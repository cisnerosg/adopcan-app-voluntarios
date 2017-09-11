package com.adopcan.adopcan_voluntarios.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adopcan.adopcan_voluntarios.CustomHttpRequest.AppController;
import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.DTO.State;
import com.adopcan.adopcan_voluntarios.DTO.TagReport;
import com.adopcan.adopcan_voluntarios.R;
import com.adopcan.adopcan_voluntarios.Service.ImageService;
import com.adopcan.adopcan_voluntarios.Utils.Circle;
import com.adopcan.adopcan_voluntarios.Utils.DateUtils;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.R.attr.tag;

/**
 * Created by german on 12/8/2017.
 */

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private static final String TAG = "CustomInfoWindowAdapter";
    private LayoutInflater inflater;
    private boolean not_first_time_showing_info_window = false;
    public CustomInfoWindowAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public View getInfoContents(final Marker m) {

        //Carga layout en el mapa
        TagReport tag = (TagReport) m.getTag();

        if(tag != null && tag.getReport() != null) {

            Report report = tag.getReport();

            ConstraintLayout layout = (ConstraintLayout) ((Activity) tag.getContext()).findViewById(R.id.constraintLayout_tag);
            layout.setVisibility(View.VISIBLE);

            TextView description = (TextView) ((Activity) tag.getContext()).findViewById(R.id.textView_description);
            description.setText(report.getDescription());

            TextView date = (TextView) ((Activity) tag.getContext()).findViewById(R.id.textView_date);
            DateUtils dateUtils = new DateUtils();
            date.setText("Reportado el " + dateUtils.getDateDescription(report.getDate()));

            return null;
        }
        return null;
    }

    private class InfoWindowRefresher implements Callback {
        private Marker markerToRefresh;

        private InfoWindowRefresher(Marker markerToRefresh) {
            this.markerToRefresh = markerToRefresh;
        }

        @Override
        public void onSuccess() {
            markerToRefresh.showInfoWindow();
        }

        @Override
        public void onError() {}
    }

    @Override
    public View getInfoWindow(Marker m){

        return  null;
    }

}



//    @Override
//    public View getInfoContents(final Marker m) {
//
//        //Carga layout personalizado.
//        TagReport tag = (TagReport) m.getTag();
//
//        if(tag != null && tag.getReport() != null) {
//            Report report = tag.getReport();
//
//            ConstraintLayout layout = (ConstraintLayout) ((Activity) tag.getContext()).findViewById(R.id.constraintLayout_tag);
//            layout.setVisibility(View.VISIBLE);
//
//            TextView description = (TextView) ((Activity) tag.getContext()).findViewById(R.id.textView_description);
//            description.setText(report.getDescription());
//
//            TextView date = (TextView) ((Activity) tag.getContext()).findViewById(R.id.textView_date);
//            date.setText(report.getDate().toString());
//
//            TextView state = (TextView) ((Activity) tag.getContext()).findViewById(R.id.textView_state);
//            date.setText(report.getEnumState().getDescription());
//
//            View v = inflater.inflate(R.layout.activity_info_window_map, null);
//            String[] info = m.getTitle().split("&");
//            String url = m.getSnippet();
//
//            ImageView imageView = v.findViewById(R.id.info_window_imagen);
////            String urlImage = "http://www.adopcan.com/uploads/" + report.getFilename();
//            String urlImage = "https://static.hogarmania.com/archivos/201505/perro-consejos-416x236x80xX.jpg";
//
//            if (not_first_time_showing_info_window) {
//                Picasso.with(tag.getContext()).load(urlImage).into(imageView);
//            } else { // if it's the first time, load the image with the callback set
//                not_first_time_showing_info_window=true;
//                Picasso.with(tag.getContext()).load(urlImage).into(imageView,new InfoWindowRefresher(m));
//            }
//
//
//            ((TextView) v.findViewById(R.id.info_window_description)).setText(report.getDescription());
//
//            DateUtils dateUtils = new DateUtils();
//            Date dateReport =  dateUtils.getDateByString(report.getCreatedAt());
//
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
//            String reportDate = sdf.format(dateReport);
//
//            sdf = new SimpleDateFormat("H:mm");
//            String reportHour = sdf.format(dateReport);
//            if(reportDate != null && !reportDate.equals("") && reportHour != null && !reportHour.equals("")) {
//                ((TextView) v.findViewById(R.id.info_window_date)).setText("Día: " + reportDate);
//                ((TextView) v.findViewById(R.id.info_window_hour)).setText("Hora: " + reportHour);
//            }
//
//            ((TextView) v.findViewById(R.id.info_window_state)).setText("Estado: "+report.getEnumState().getDescription());
//
//            if(report.getEnumState().equals(State.ACTIVE)) {
//                ((TextView) v.findViewById(R.id.info_window_inform)).setText("Reporta que el perro fue rescatado manteniendo presionado");
//            }else if(report.getEnumState().equals(State.RESCUED)){
//                ((TextView) v.findViewById(R.id.info_window_inform)).setText("Reporta que el perro sigue perdido manteniendo presionado");
//            }
//            return v;
//        }
//        return null;
//    }



