package com.adopcan.adopcan_voluntarios.Service;

import com.adopcan.adopcan_voluntarios.Utils.HttpMethod;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Aurelio on 17/10/2017.
 */

public class OrganizationService {

    public StringRequest getOrganizations(Response.Listener<String> responseListener, Response.ErrorListener errorListener){
        String url = "http://www.adopcan.com/api/organizaciones";
        HttpMethod httpMethod = new HttpMethod();
        return httpMethod.httpGetMethod(url,responseListener, errorListener);

    }
}
