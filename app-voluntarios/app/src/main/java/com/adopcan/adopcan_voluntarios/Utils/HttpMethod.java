package com.adopcan.adopcan_voluntarios.Utils;

import android.content.Context;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.AppController;
import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by german on 21/8/2017.
 */

public class HttpMethod {
    public StringRequest httpPostMethod( String url, Map<String, String> parameters, Response.Listener<String> responseListener,Response.ErrorListener errorListener){
        final Map<String, String> params = parameters;

        StringRequest request = new StringRequest(Request.Method.POST, url,responseListener,errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return request;
    }

    public StringRequest httpGetMethod(String url, Response.Listener<String> responseListener,Response.ErrorListener errorListener){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                responseListener,errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", SecurityHandler.getSecurity().getUser().getResponseToken().getAutorization());
                return headers;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return stringRequest;

    }
}
