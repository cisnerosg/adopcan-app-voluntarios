package com.adopcan.adopcan_voluntarios.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by german on 12/8/2017.
 */

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{

    private static final String TAG = "CustomInfoWindowAdapter";
    private LayoutInflater inflater;

    public CustomInfoWindowAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public View getInfoContents(final Marker m) {
        //Carga layout personalizado.
        Report report = (Report) m.getTag();

        if(report != null) {
            View v = inflater.inflate(R.layout.activity_info_window_map, null);
            String[] info = m.getTitle().split("&");
            String url = m.getSnippet();
            ((TextView) v.findViewById(R.id.info_window_description)).setText(report.getDescription());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            String reportDate = sdf.format(report.getDate());

            sdf = new SimpleDateFormat("H:mm");
            String reportHour = sdf.format(report.getDate());
            ((TextView) v.findViewById(R.id.info_window_date)).setText("Día: " + reportDate);
            ((TextView) v.findViewById(R.id.info_window_hour)).setText("Hora: " + reportHour);
            return v;
        }
        return null;
    }

    @Override
    public View getInfoWindow(Marker m) {
        return null;
    }

}
