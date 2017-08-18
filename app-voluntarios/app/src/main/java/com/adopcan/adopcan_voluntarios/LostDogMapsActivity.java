package com.adopcan.adopcan_voluntarios;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.adopcan.adopcan_voluntarios.Adapter.CustomInfoWindowAdapter;
import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.DTO.Ubication;
import com.adopcan.adopcan_voluntarios.Service.ReportService;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static com.adopcan.adopcan_voluntarios.R.drawable.dog;

public class LostDogMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marker;
    private double lat = 0.0;
    private double lon = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_lost_dog_maps);
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

        ReportService service = new ReportService();
        //marcadores de perros perdidos
        List<Report> reports = service.getListDog();

        for(Report r : reports) {
            LatLng coor = new LatLng(r.getUbication().getLatitud(), r.getUbication().getLongitud()
            );
            CameraUpdate ubication = CameraUpdateFactory.newLatLngZoom(coor, 15);
            Marker marker = mMap.addMarker(new MarkerOptions().position(coor).title("Mi Ubicación").icon(BitmapDescriptorFactory.fromResource(dog)).snippet("Si querés cambiar la posición arrastrá el marcador"));

            marker.setTag(r);
            mMap.animateCamera(ubication);
        }

        /*Mi ubicacion*/
        Ubication myUbication = myUbication();
        LatLng coor = new LatLng(myUbication.getLatitud(), myUbication.getLongitud());
        CameraUpdate ubication = CameraUpdateFactory.newLatLngZoom(coor,17);
        if (marker != null) {
            marker.remove();
        }

        marker = mMap.addMarker(new MarkerOptions().position(coor).title("Mi Ubicación").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)).draggable(true));
        mMap.animateCamera(ubication);
    }


    private Ubication myUbication() {

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Ubication ubication = updateUbication(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,20000,0,locListener);
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

}
