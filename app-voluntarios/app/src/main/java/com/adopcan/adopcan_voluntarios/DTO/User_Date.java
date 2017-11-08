package com.adopcan.adopcan_voluntarios.DTO;

import com.adopcan.adopcan_voluntarios.Security.ResponseToken;
import com.google.gson.annotations.SerializedName;

/**
 * Created by german on 20/8/2017.
 */

public class User_Date {

    @SerializedName("dieta")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
