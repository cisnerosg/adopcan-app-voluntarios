package com.adopcan.adopcan_voluntarios.Service;

import com.adopcan.adopcan_voluntarios.DTO.CalendarInfo;
import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.Mock.CalendarInfoMock;
import com.adopcan.adopcan_voluntarios.Mock.DogMock;
import com.adopcan.adopcan_voluntarios.Utils.HttpMethod;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

/**
 * Created by german on 17/8/2017.
 */

public class CalendarInfoService {

    public StringRequest getCalendarDogs(Response.Listener<String> responseListener, Response.ErrorListener errorListener){
        String url = "http://www.adopcan.com/api/agenda";
        HttpMethod httpMethod = new HttpMethod();
        return httpMethod.httpGetMethod(url,responseListener, errorListener);

    }
}
