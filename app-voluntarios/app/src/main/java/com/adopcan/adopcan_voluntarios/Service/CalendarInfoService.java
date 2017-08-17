package com.adopcan.adopcan_voluntarios.Service;

import com.adopcan.adopcan_voluntarios.DTO.CalendarInfo;
import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.Mock.CalendarInfoMock;
import com.adopcan.adopcan_voluntarios.Mock.DogMock;

import java.util.List;

/**
 * Created by german on 17/8/2017.
 */

public class CalendarInfoService {

    public List<CalendarInfo> getCalendarDogsByVoluntaryId(long id){
        CalendarInfoMock calendarInfoMock = new CalendarInfoMock();

        return calendarInfoMock.getListCalendarInfo();

    }
}
