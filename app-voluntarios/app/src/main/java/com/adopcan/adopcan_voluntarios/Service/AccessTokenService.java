package com.adopcan.adopcan_voluntarios.Service;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by german on 20/8/2017.
 */

public class AccessTokenService {

    public StringRequest getAccessToken(String username, String pass, Response.Listener<String> responseListener, Response.ErrorListener errorListener){

        final Map<String, String> params = new LinkedHashMap<>();
        params.put("grant_type", "password");
        params.put("client_id", "android");
        params.put("client_secret", "$2y$10$i7jFI84uPuuzqbAT.tJyb.h1xggXRVK3Onj8XpLGhdOzfKhhcReiC");
        params.put("scope", "default");
        params.put("username", "ong@yopmail.com");
        params.put("password", "12345678");

        String url=  "http://www.adopcan.com/api/access_token";

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
}
