package com.adopcan.adopcan_voluntarios;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ListView;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.AppController;
import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.DTO.OrganizationTemp;
import com.adopcan.adopcan_voluntarios.Service.DogService;
import com.adopcan.adopcan_voluntarios.Service.OrganizationService;
import com.adopcan.adopcan_voluntarios.Utils.DateUtils;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Aurelio on 17/10/2017.
 */

public class OrganizationActivity extends AppCompatActivity implements  Response.ErrorListener, Response.Listener<String>{

    OrganizationService organizationService;
    private List<OrganizationTemp> organizaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_organization);
        initListOrganization();
    }

    @Override
    public void onResponse(String response) {

        try {
            organizaciones= getListFromJson(response);
            ListView listView = (ListView)findViewById(R.id.listView_dogs);
//            OrganizationActivity.CustomAdapter customAdapter = new OrganizationActivity.CustomAdapter();
//            listView.setAdapter(customAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onErrorResponse(VolleyError error) {

        if (error == null || error.networkResponse == null) {
            return;
        }

        String body;
        //get status code here
        final String statusCode = String.valueOf(error.networkResponse.statusCode);
        //get response body and parse with appropriate encoding
        try {
            body = new String(error.networkResponse.data,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // exception
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    /*busca las organizaciones y las agrega en el listview*/
    private void initListOrganization(){
        organizationService = new OrganizationService();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Request<?> request = organizationService.getOrganizations(this, this);
        AppController.getInstance().addToRequestQueue(request);

    }

    private static final List<OrganizationTemp> getListFromJson(String json) throws Exception {
        Gson gson = new GsonBuilder().create();
        Type typeOfList = new TypeToken<List<OrganizationTemp>>(){}.getType();
        return gson.fromJson(json, typeOfList);
    }


}