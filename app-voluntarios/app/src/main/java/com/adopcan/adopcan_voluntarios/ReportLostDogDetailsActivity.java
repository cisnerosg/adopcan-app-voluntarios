package com.adopcan.adopcan_voluntarios;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.AppController;
import com.adopcan.adopcan_voluntarios.CustomHttpRequest.DefaultExclusionStrategy;
import com.adopcan.adopcan_voluntarios.DTO.MessageAlert;
import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.DTO.State;
import com.adopcan.adopcan_voluntarios.Security.ResponseToken;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.adopcan.adopcan_voluntarios.Service.ReportService;
import com.adopcan.adopcan_voluntarios.Utils.SaveImage;
import com.adopcan.adopcan_voluntarios.Volley.AppHelper;
import com.adopcan.adopcan_voluntarios.Volley.VolleyMultipartRequest;
import com.adopcan.adopcan_voluntarios.Volley.VolleySingleton;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ReportLostDogDetailsActivity extends AppCompatActivity {

    private Report report;
    private com.adopcan.adopcan_voluntarios.Utils.AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        report = (Report)this.getIntent().getExtras().get("report");
        alertDialog = new com.adopcan.adopcan_voluntarios.Utils.AlertDialog();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_lost_dog_details);

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

    private Report getReportWithDescription(String description){
        report.setDescription(description);

        return report;
    }

    public void sendReport(View view){

        String description = ((EditText)findViewById(R.id.editText_description)).getText().toString();

        if(description.equals("")){
            alertDialog.showAlertWithAcept(this, "Alerta", "Tenés que agregar un comentanrio, acordate que mientras mas describas más fácil lo podrán reconocer");
        }else {
            report.setDescription(description);
            report.setState(State.ACTIVE.getId());
            ReportService reportService = new ReportService();
            VolleyMultipartRequest multipartRequest = reportService.saveReport(report);
            VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);
            login();
        }
    }

    private void login(){
        Intent intent = new Intent(this, SolapaActivity.class);
        MessageAlert msg = new MessageAlert("Gracias!!","Hemos recibido tu reporte, con tu ayuda muchos perros podrán encontrar un hogar");
        intent.putExtra ("message", msg);
        startActivity(intent);
    }

    public VolleyMultipartRequest saveReport() {
        // loading or check internet connection or something...
        // ... then
        String url = "http://www.adopcan.com/api/reporte/nuevo";
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
                alertDialog.showAlertWithAcept(getApplicationContext(), "Alerta","hubo un problema en la aplicación, intentalo mas tarde");

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
                params.put("coords", getCoords());
                params.put("descripcion", report.getDescription());
                params.put("estado", "1");
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                params.put("foto", new DataPart("file_image.jpg", report.getPhotoByte(), "image/jpeg"));

                return params;
            }
        };

//        VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);
        return multipartRequest;
    }

    private String getCoords(){
        String lat = Double.toString(report.getLatitude() + 4 );
        String lon = Double.toString(report.getLongitude() + 4);

        return lat + "," + lon;
    }
}
