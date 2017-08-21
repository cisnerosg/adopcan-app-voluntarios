package com.adopcan.adopcan_voluntarios.Service;

import android.content.Context;
import android.util.Log;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.DefaultExclusionStrategy;
import com.adopcan.adopcan_voluntarios.DTO.User;
import com.adopcan.adopcan_voluntarios.Security.ResponseToken;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.adopcan.adopcan_voluntarios.Utils.HttpMethod;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

        String url = "http://www.adopcan.com/api/access_token";

        HttpMethod httpMethod = new HttpMethod();
        return httpMethod.httpPostMethod(url, params, responseListener,errorListener);

    }

}
