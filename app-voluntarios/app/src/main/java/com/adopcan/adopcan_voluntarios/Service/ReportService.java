package com.adopcan.adopcan_voluntarios.Service;

import com.adopcan.adopcan_voluntarios.DTO.Report;
import com.adopcan.adopcan_voluntarios.Mock.ReportMock;

import java.util.ArrayList;
import java.util.List;

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

}
