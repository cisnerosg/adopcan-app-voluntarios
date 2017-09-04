package com.adopcan.adopcan_voluntarios.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by german on 16/8/2017.
 */

public class CalendarInfo {

    @SerializedName("id")
    private String id;
    @SerializedName("fecha")
    private String date;
    @SerializedName("lugar")
    private String place;
    @SerializedName("descripcion")
    private String description;
    @SerializedName("animal")
    private DogTemp dog;


    public CalendarInfo(String id, String date, String description, DogTemp dog, String place) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.dog = dog;
        this.place = place;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DogTemp getDog() {
        return dog;
    }

    public void setDog(DogTemp dog) {
        this.dog = dog;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
