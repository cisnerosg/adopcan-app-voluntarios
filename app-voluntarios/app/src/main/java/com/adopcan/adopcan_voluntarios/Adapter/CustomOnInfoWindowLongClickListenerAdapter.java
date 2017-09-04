package com.adopcan.adopcan_voluntarios.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.DTO.State;
import com.adopcan.adopcan_voluntarios.DTO.TagReport;
import com.adopcan.adopcan_voluntarios.LostDogMapsActivity;
import com.adopcan.adopcan_voluntarios.R;
import com.adopcan.adopcan_voluntarios.Service.ReportService;
import com.adopcan.adopcan_voluntarios.Utils.DateUtils;
import com.adopcan.adopcan_voluntarios.Volley.VolleyMultipartRequest;
import com.adopcan.adopcan_voluntarios.Volley.VolleySingleton;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by german on 12/8/2017.
 */

public class CustomOnInfoWindowLongClickListenerAdapter implements GoogleMap.OnInfoWindowLongClickListener {

    private static final String TAG = "CustomOnInfoWindowLongClickListenerAdapter";
    private LayoutInflater inflater;

    public CustomOnInfoWindowLongClickListenerAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public void onInfoWindowLongClick(Marker marker) {

        View v = inflater.inflate(R.layout.activity_info_window_map, null);
        TagReport tag = (TagReport) marker.getTag();

        if(tag.getReport().getEnumState().equals(State.ACTIVE)) {
            tag.getReport().setState(State.RESCUED.getId());

            Toast.makeText(tag.getContext(), "El rescate fue reportado, gracias por colaborar!",
                    Toast.LENGTH_SHORT).show();
        }else if(tag.getReport().getEnumState().equals(State.RESCUED)){
            tag.getReport().setState(State.ACTIVE.getId());

            Toast.makeText(tag.getContext(), "La modifcación ya fue realizada, gracias por colaborar!",
                    Toast.LENGTH_SHORT).show();
        }
        LostDogMapsActivity lost = new LostDogMapsActivity();
        ReportService reportService = new ReportService();
        VolleyMultipartRequest multipartRequest = reportService.editReport(tag.getReport());
        VolleySingleton.getInstance(tag.getContext()).addToRequestQueue(multipartRequest);
        marker.showInfoWindow();
    }
}


