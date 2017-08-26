package com.adopcan.adopcan_voluntarios.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by german on 17/8/2017.
 */

public class DateUtils {


    public String getDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
       return  sdf.format(date);
    }


    public String getHour(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
        return  sdf.format(date);
    }

    public Date getDateByString(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return date;
    }
}
