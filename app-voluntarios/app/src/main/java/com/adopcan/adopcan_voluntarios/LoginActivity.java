package com.adopcan.adopcan_voluntarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.AppController;
import com.adopcan.adopcan_voluntarios.CustomHttpRequest.DefaultExclusionStrategy;
import com.adopcan.adopcan_voluntarios.DTO.User;
import com.adopcan.adopcan_voluntarios.DTO.UserType;
import com.adopcan.adopcan_voluntarios.Security.ResponseToken;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.adopcan.adopcan_voluntarios.Service.AccessTokenService;
import com.adopcan.adopcan_voluntarios.Utils.AlertDialog;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {

    private User user;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alertDialog = new AlertDialog();
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }

    public void login(View view){

        EditText username = ((EditText)findViewById(R.id.editText_username));
        EditText pass = ((EditText)findViewById(R.id.editText_pass));

        if(username.getText()== null || username.getText().toString().equals("") || pass.getText()==null || pass.getText().toString().equals("")){
            alertDialog.showAlertWithAcept(this, "Alerta", "Completá el usuario y la contraseña para ingresar");
        }else{

            user = new User(username.getText().toString(), pass.getText().toString(), UserType.SIMPLE);

            AccessTokenService accessTokenService = new AccessTokenService();
            Request<?> request = accessTokenService.getAccessToken(user.getUsername(), user.getPassword(),this, this);
            AppController.getInstance().addToRequestQueue(request);
        }
    }

    @Override
    public void onResponse(String response) {
        Log.i("login correcto",response);
        GsonBuilder builder = new GsonBuilder();
        builder.setExclusionStrategies(new DefaultExclusionStrategy());
        Gson json = builder.create();
        ResponseToken responseToken = json.fromJson(response,ResponseToken.class);
        user.addResponseToken(responseToken);
        SecurityHandler.getInstance(user);

        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
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
        alertDialog.showAlertWithAcept(this, "Alerta", "El usuario o la contraseña no son válidos");

    }
}
