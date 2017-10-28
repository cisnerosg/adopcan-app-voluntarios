package com.adopcan.adopcan_voluntarios.Service;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.DefaultExclusionStrategy;
import com.adopcan.adopcan_voluntarios.DTO.Account;
import com.adopcan.adopcan_voluntarios.DTO.OrganizationTemp;
import com.adopcan.adopcan_voluntarios.Security.ResponseToken;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.adopcan.adopcan_voluntarios.Utils.HttpMethod;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class AccountService {

    public StringRequest fillAccount(Response.Listener<String> responseListener, Response.ErrorListener errorListener){
        OrganizationTemp org = SecurityHandler.getSecurity().getUser().getOrganization();

        String url = "http://www.adopcan.com/api/" + org.getId()  +"/me";
        HttpMethod httpMethod = new HttpMethod();
        return httpMethod.httpGetMethod(url, responseListener,errorListener);

    }
}
