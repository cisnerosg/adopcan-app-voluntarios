package com.adopcan.adopcan_voluntarios.Mock;

import com.adopcan.adopcan_voluntarios.DTO.CalendarInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by german on 17/8/2017.
 */

public class CalendarInfoMock {

    public CalendarInfo getCalendarInfo(Date date, Date hour,String description, String name){
        DogMock dogMock = new DogMock();

        CalendarInfo calendar = new CalendarInfo(1, date, hour, description, dogMock.getDog(1,name));
        return calendar;
    }

    public List<CalendarInfo> getListCalendarInfo(){
        List<CalendarInfo> list = new ArrayList<CalendarInfo>();
        list.add(getCalendarInfo(new Date(), new Date(), "vacunacion anual", "Manchitas"));
        list.add(getCalendarInfo(new Date(), new Date(), "control general", "Popi"));
        list.add(getCalendarInfo(new Date(), new Date(), "vuelve al refugio", "Manchitas"));
        list.add(getCalendarInfo(new Date(), new Date(), "vacunacion antirrabica", "Juana"));

        return list;
    }
}
