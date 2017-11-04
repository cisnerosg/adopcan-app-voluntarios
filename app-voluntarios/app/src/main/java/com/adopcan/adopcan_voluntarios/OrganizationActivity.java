package com.adopcan.adopcan_voluntarios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.AppController;
import com.adopcan.adopcan_voluntarios.CustomHttpRequest.DefaultExclusionStrategy;
import com.adopcan.adopcan_voluntarios.DTO.Account;
import com.adopcan.adopcan_voluntarios.DTO.OrganizationTemp;
import com.adopcan.adopcan_voluntarios.Security.ResponseToken;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.adopcan.adopcan_voluntarios.Service.AccountService;
import com.adopcan.adopcan_voluntarios.Service.OrganizationService;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void fillUser(OrganizationTemp org){

        SecurityHandler.getSecurity().getUser().setOrganization(org);

        AccountService accountService = new AccountService();
        Request<?> request = accountService.fillAccount(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson json = builder.create();
                Account account = json.fromJson(response,Account.class);

                SecurityHandler.getSecurity().getUser().setAccount(account);
                SecurityHandler.getSecurity().getUser().setCountOrganization(organizaciones.size());

                Intent intent = new Intent(getApplicationContext(), SolapaActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String err = "ocurrio un error";

                Intent intent = new Intent(getApplicationContext(), SolapaActivity.class);
                startActivity(intent);
            }
        });
        AppController.getInstance().addToRequestQueue(request);




    }

    @Override
    public void onResponse(String response) {

        try {
            organizaciones= getListFromJson(response);
            if(organizaciones.size() > 1) {
                ListView listView = (ListView) findViewById(R.id.listView_Organization);
                OrganizationActivity.CustomAdapter customAdapter = new OrganizationActivity.CustomAdapter();
                listView.setAdapter(customAdapter);
            } else {
                fillUser(organizaciones.get(0));
            }

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

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return organizaciones.size();
        }

        @Override
        public Object getItem(int i) {
            return organizaciones.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.organization_list_info, null);

            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView orgName = (TextView)view.findViewById(R.id.org_name);
            TextView orgDir = (TextView)view.findViewById(R.id.org_dir);
            TextView orgWeb = (TextView)view.findViewById(R.id.org_webSite);
            orgName.setText(organizaciones.get(i).getName());
            orgDir.setText(organizaciones.get(i).getAddress());
            orgWeb.setText(organizaciones.get(i).getTelephone());

            Button button = (Button)view.findViewById(R.id.seleccionar_organizacion);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                fillUser(organizaciones.get(i));
                }
            });
            return view;
        }
    }




}
