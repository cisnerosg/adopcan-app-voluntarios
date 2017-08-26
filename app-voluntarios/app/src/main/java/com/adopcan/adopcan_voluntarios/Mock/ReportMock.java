package com.adopcan.adopcan_voluntarios.Mock;

import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.DTO.Ubication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by german on 12/8/2017.
 */

public class ReportMock {

    public List<Report> listMock(){
        List<Report> list = new ArrayList<Report>();
        list.add(buildReport("Es mediano y tiene manchas negras",-35.670133, -59.7085113, new Date()));
        list.add(buildReport("Es todo blanco y tiene una mancha marron en el ojo",-35.970133, -58.6085123, new Date()));
        list.add(buildReport("Es todo negro",-36.570133, -59.6795113, new Date()));
        list.add(buildReport("Le falta una patita",-35.820133, -59.9085113, new Date()));

        return list;
    }


    public Report buildReport(String desc, double lat, double lon, Date fecha){
        Report report = new Report();
        report.setDescription(desc);
        report.setLatitude(lat);
        report.setLongitude(lon);
        report.setDate(fecha);

        return report;

    }



}
