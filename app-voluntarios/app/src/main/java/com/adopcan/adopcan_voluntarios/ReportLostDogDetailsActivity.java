package com.adopcan.adopcan_voluntarios;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.AppController;
import com.adopcan.adopcan_voluntarios.CustomHttpRequest.DefaultExclusionStrategy;
import com.adopcan.adopcan_voluntarios.DTO.MessageAlert;
import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.DTO.State;
import com.adopcan.adopcan_voluntarios.Security.ResponseToken;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.adopcan.adopcan_voluntarios.Service.AccessTokenService;
import com.adopcan.adopcan_voluntarios.Service.ReportService;
import com.adopcan.adopcan_voluntarios.Utils.SaveImage;
import com.adopcan.adopcan_voluntarios.Volley.AppHelper;
import com.adopcan.adopcan_voluntarios.Volley.VolleyMultipartRequest;
import com.adopcan.adopcan_voluntarios.Volley.VolleySingleton;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.min;
import static com.adopcan.adopcan_voluntarios.R.id.textView;
import static com.adopcan.adopcan_voluntarios.R.id.toggleButtonFB;

public class ReportLostDogDetailsActivity extends AppCompatActivity {

    private Report report;
    private com.adopcan.adopcan_voluntarios.Utils.AlertDialog alertDialog;
    private CallbackManager callbackManager;

    //FB
    JSONObject response, profile_pic_data, profile_pic_url;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        report = (Report)this.getIntent().getExtras().get("report");
        alertDialog = new com.adopcan.adopcan_voluntarios.Utils.AlertDialog();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_lost_dog_details);
        LinearLayout linearFacebook = (LinearLayout) findViewById(R.id.linear_facebook);
        //TextView textViewCompartir = (TextView) findViewById(R.id.compartirText);
        //TextView textViewFacebook = (TextView) findViewById(R.id.facebookText);
        if(Profile.getCurrentProfile() != null){
            linearFacebook.setVisibility(View.VISIBLE);
        } else {
            linearFacebook.setVisibility(View.INVISIBLE);
        }


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
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButtonFB);

        if(description.equals("")){
            alertDialog.showAlertWithAcept(this, "Alerta", "Tenés que agregar un comentanrio, acordate que mientras mas describas más fácil lo podrán reconocer");
        }else {

            if(toggle.getVisibility() == View.VISIBLE && toggle.isChecked()){
                report.setShareFacebook(true);
            }

            report.setDescription(description);
            report.setState(State.ACTIVE.getId());
            ReportService reportService = new ReportService();
            VolleyMultipartRequest multipartRequest = reportService.saveReport(report);
            DefaultRetryPolicy  retryPolicy = new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            multipartRequest.setRetryPolicy(retryPolicy);
            VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);
         //   compartirReporteFacebook();
            login();
        }
    }

    private void compartirReporteFacebook() {
        final ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButtonFB);
        if (toggle.isChecked()) {
            // The toggle is enabled
            if(Profile.getCurrentProfile() != null && AccessToken.getCurrentAccessToken() != null){
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder builder = new GsonBuilder();
                        builder.setExclusionStrategies(new DefaultExclusionStrategy());
                        Gson json = builder.create();
                        ResponseToken responseToken = json.fromJson(response,ResponseToken.class);
                        SecurityHandler.getSecurity().getUser().addResponseToken(responseToken);
                        Intent intent = new Intent(ReportLostDogDetailsActivity.this, SolapaActivity.class);
                        startActivity(intent);
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
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
                };


                AccessTokenService accessTokenService = new AccessTokenService();
                Request<?> request = accessTokenService.getAccessTokenFB(AccessToken.getCurrentAccessToken().getToken(), responseListener, errorListener);
                AppController.getInstance().addToRequestQueue(request);
            }
        } else {
            // The toggle is disabled
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

    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
