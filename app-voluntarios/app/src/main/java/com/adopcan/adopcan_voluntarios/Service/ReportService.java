package com.adopcan.adopcan_voluntarios.Service;

import android.content.Context;

import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.Mock.ReportMock;
import com.adopcan.adopcan_voluntarios.Utils.HttpMethod;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by german on 12/8/2017.
 * Comunicacion via Rest para datos de todos que tienen que ver con los reportes de perros perdidos
 */

public class ReportService {

    public void sendReport(Report report){
        /*traer la foto y desp mandarlo*/
        return;
    }

    public List<Report> getListDog(){
        List<Report> dogs = new ArrayList<Report>();

        ReportMock mock = new ReportMock();
        dogs = mock.listMock();

        return dogs;
    }

    public StringRequest getReports(Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        List<Report> list = new ArrayList<Report>();
        String url = "http://www.adopcan.com/api/organizaciones";

        HttpMethod httpMethod = new HttpMethod();
        return httpMethod.httpGetMethod(url,responseListener, errorListener);

    }


}
