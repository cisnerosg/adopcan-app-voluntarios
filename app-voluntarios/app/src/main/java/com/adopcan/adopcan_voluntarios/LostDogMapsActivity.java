package com.adopcan.adopcan_voluntarios;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adopcan.adopcan_voluntarios.Adapter.CustomInfoWindowAdapter;
import com.adopcan.adopcan_voluntarios.Adapter.CustomOnInfoWindowLongClickListenerAdapter;
import com.adopcan.adopcan_voluntarios.CustomHttpRequest.AppController;
import com.adopcan.adopcan_voluntarios.CustomHttpRequest.DefaultExclusionStrategy;
import com.adopcan.adopcan_voluntarios.DTO.MessageAlert;
import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.DTO.State;
import com.adopcan.adopcan_voluntarios.DTO.TagReport;
import com.adopcan.adopcan_voluntarios.DTO.Ubication;
import com.adopcan.adopcan_voluntarios.Mock.ReportMock;
import com.adopcan.adopcan_voluntarios.Security.ReportTest;
import com.adopcan.adopcan_voluntarios.Security.ResponseToken;
import com.adopcan.adopcan_voluntarios.Security.SecurityHandler;
import com.adopcan.adopcan_voluntarios.Service.AccessTokenService;
import com.adopcan.adopcan_voluntarios.Service.ReportService;
import com.adopcan.adopcan_voluntarios.Utils.AlertDialog;
import com.adopcan.adopcan_voluntarios.Utils.JsonUtils;
import com.adopcan.adopcan_voluntarios.Volley.VolleyMultipartRequest;
import com.adopcan.adopcan_voluntarios.Volley.VolleySingleton;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.tag;
import static com.adopcan.adopcan_voluntarios.R.drawable.dog;
import static com.adopcan.adopcan_voluntarios.R.drawable.markerboy;

public class LostDogMapsActivity extends AppCompatActivity implements OnMapReadyCallback, Response.ErrorListener, Response.Listener<String> {

    private GoogleMap mMap;
    private Marker marker;
    private double lat = 0.0;
    private double lon = 0.0;
    private JsonUtils jsonUtils;
    private List<Report> listReport = new ArrayList<Report>();
    private LayoutInflater inflater;

    public LostDogMapsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_lost_dog_maps);
        jsonUtils = new JsonUtils();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(LayoutInflater.from(this)));

        mMap.setOnInfoWindowLongClickListener(new CustomOnInfoWindowLongClickListenerAdapter(LayoutInflater.from(this)));

        mMap.setOnInfoWindowCloseListener(new GoogleMap.OnInfoWindowCloseListener() {
            @Override
            public void onInfoWindowClose(Marker marker) {
                TagReport tag = (TagReport) marker.getTag();
                if(tag != null && tag.getReport() != null) {
                    ConstraintLayout layout = (ConstraintLayout) ((Activity) tag.getContext()).findViewById(R.id.constraintLayout_tag);
                    layout.setVisibility(View.INVISIBLE);
                }
            }

        });
        ReportService service = new ReportService();

        ////////////////////////////////////////////////////////////////

        ReportService reportService = new ReportService();
        Request<?> request = reportService.getReports(this, this);
        AppController.getInstance().addToRequestQueue(request);


    }


    private Ubication myUbication() {

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Ubication ubication = updateUbication(location);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,20000,0,locListener);
        return ubication;

    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateUbication(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    private Ubication updateUbication(Location location) {

        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();

        }

        return new Ubication(lat, lon);
    }

    @Override
    public void onResponse(String response) {



        try {
            listReport= getListFromJson(response);
            fillMap(listReport);
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


    private static final List<Report> getListFromJson(String json) throws Exception {
        Gson gson = new GsonBuilder().create();
        Type typeOfList = new TypeToken<List<Report>>(){}.getType();
        return gson.fromJson(json, typeOfList);
    }

    private void fillMap(List<Report> reports){
        mMap.clear();
        for(Report r : reports) {
            LatLng coor = new LatLng(r.getLatitude(), r.getLongitude());
            CameraUpdate ubication = CameraUpdateFactory.newLatLngZoom(coor, 15);
            Marker marker = mMap.addMarker(new MarkerOptions().position(coor).title("Perro Seleccionado").icon(BitmapDescriptorFactory.fromResource(dog)));

            TagReport tag = new TagReport(r, this);
            marker.setTag(tag);

            mMap.animateCamera(ubication);
        }

        /*Mi ubicacion*/
        Ubication myUbication = myUbication();
        Double corrimiento = 0.0000001;

        LatLng coor = new LatLng(myUbication.getLatitud() + corrimiento , myUbication.getLongitud());
        CameraUpdate ubication = CameraUpdateFactory.newLatLngZoom(coor,17);
        if (marker != null) {
            marker.remove();
        }

        marker = mMap.addMarker(new MarkerOptions().position(coor).title("Mi Ubicación").icon(BitmapDescriptorFactory.fromResource(markerboy)).draggable(true));
        mMap.animateCamera(ubication);
    }

    public void changeState(View view){
        AlertDialog alertDialog = new AlertDialog();
        showAlertWithAceptAndCancel(this, "Alerta", "Estás seguro que vas a rescatar al perro seleccionado? Una vez realizada la operación el perro dejará de aparecer en el mapa.");
    }

    public void showAlertWithAceptAndCancel(Context context, String title, String description) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle(title).

                setMessage(description)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick (DialogInterface dialog,int which){
                        //cambiar estado
                        TextView textViewState = (TextView) findViewById(R.id.textView_description);
                        textViewState.setText(State.RESCUED.getDescription());

                        TextView textViewReportId = (TextView) findViewById(R.id.TextView_reportId);
                        String id = (String) textViewReportId.getText();

                        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.constraintLayout_tag);
                        layout.setVisibility(View.INVISIBLE);

                        Toast.makeText(getApplicationContext(), "El rescate fue reportado con éxito, gracias por colaborar!",
                                    Toast.LENGTH_SHORT).show();

                        ReportService reportService = new ReportService();
                        VolleyMultipartRequest multipartRequest = reportService.changeStateReport(id,State.RESCUED);
                        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(multipartRequest);

                        updateReportById(id);

                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick (DialogInterface dialog,int which){
                // do nothing
            }
        }).setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    private void updateReportById(String id){
        for(Report r : listReport){
            if(r.getId().equals(id)){
                listReport.remove(r);
                break;
            }
        }

        fillMap(listReport);

    }
}
