package com.adopcan.adopcan_voluntarios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.AppController;
import com.adopcan.adopcan_voluntarios.DTO.OrganizationTemp;
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
    public void onResponse(String response) {

        try {
            organizaciones= getListFromJson(response);
            if(organizaciones.size() > 1) {
                ListView listView = (ListView) findViewById(R.id.listView_Organization);
                OrganizationActivity.CustomAdapter customAdapter = new OrganizationActivity.CustomAdapter();
                listView.setAdapter(customAdapter);
            } else {
                Intent intent = new Intent(this, SolapaActivity.class);
                intent.putExtra ("organizacion", organizaciones.get(0));
                startActivity(intent);
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
            TextView textName = (TextView)view.findViewById(R.id.org_name);
            textName.setText(organizaciones.get(i).getName());

            Button button = (Button)view.findViewById(R.id.seleccionar_organizacion);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), SolapaActivity.class);
                    intent.putExtra ("organizacion", organizaciones.get(i));
                    startActivity(intent);
                }
            });
            return view;
        }
    }


}
