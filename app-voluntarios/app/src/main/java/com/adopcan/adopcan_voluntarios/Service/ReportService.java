package com.adopcan.adopcan_voluntarios.Service;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;

import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.DTO.State;
import com.adopcan.adopcan_voluntarios.Mock.ReportMock;
import com.adopcan.adopcan_voluntarios.R;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.adopcan.adopcan_voluntarios.Utils.HttpMethod;
import com.adopcan.adopcan_voluntarios.Volley.VolleyMultipartRequest;
import com.adopcan.adopcan_voluntarios.Volley.VolleySingleton;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by german on 12/8/2017.
 * Comunicacion via Rest para datos de todos que tienen que ver con los reportes de perros perdidos
 */

public class ReportService {


    public List<Report> getListDog(){
        List<Report> dogs = new ArrayList<Report>();

        ReportMock mock = new ReportMock();
        dogs = mock.listMock();

        return dogs;
    }

    public StringRequest getReports(Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        String url = "http://www.adopcan.com/api/reporte";
        HttpMethod httpMethod = new HttpMethod();
        return httpMethod.httpGetMethod(url,responseListener, errorListener);

    }

    public VolleyMultipartRequest saveReport(Report report){
        String url = "http://www.adopcan.com/api/reporte/nuevo";
        return sendReport(report, url, true);
    }

    public VolleyMultipartRequest editReport(Report report){
        String url = "http://www.adopcan.com/api/reporte/" + report.getId();
        return sendReport(report, url, false);
    }
    public VolleyMultipartRequest changeStateReport(String id, State state){
        String url = "http://www.adopcan.com/api/reporte/" + id + "/estado/" + state.getId();
        return sendChangeState(url, false);
    }


    private VolleyMultipartRequest sendReport(final Report report, String url, final boolean withImage) {
        // loading or check internet connection or something...
        // ... then

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                Log.i("envio reporte correcto",resultResponse);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                Log.i("envio reporte correcto",errorMessage);

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", SecurityHandler.getSecurity().getUser().getResponseToken().getAutorization());
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("coords", getCoords(report));
                params.put("descripcion", report.getDescription());
                params.put("estado", Integer.toString(report.getState()));
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                if(withImage) {
                    params.put("foto", new DataPart("file_image.jpg", report.getPhotoByte(), "image/jpeg"));
                }
                return params;
            }
        };
        return multipartRequest;
    }

    private String getCoords(Report report){
        String lat = Double.toString(report.getLatitude());
        String lon = Double.toString(report.getLongitude());

        return lat + "," + lon;
    }

    private VolleyMultipartRequest sendChangeState(String url, final boolean withImage) {
        // loading or check internet connection or something...
        // ... then

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                Log.i("envio reporte correcto",resultResponse);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                Log.i("envio reporte correcto",errorMessage);

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", SecurityHandler.getSecurity().getUser().getResponseToken().getAutorization());
                return headers;
            }

        };
        return multipartRequest;
    }


}
