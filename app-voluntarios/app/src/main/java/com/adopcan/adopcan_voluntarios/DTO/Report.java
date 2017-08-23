package com.adopcan.adopcan_voluntarios.DTO;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by german on 12/8/2017.
 */

public class Report implements Serializable{

    @SerializedName("id")
    private String id;

    private Long userId;

    @SerializedName("foto")
    private String filename;

    private Ubication ubication;
    @SerializedName("descripcion")
    private String description;
    private Date date;
    private Bitmap photo;


    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public Report(){
        ubication = new Ubication(0.0,0.0);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUbication(Ubication ubication) {
        this.ubication = ubication;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {

        return date;
    }
    public String getId() {

        return id;
    }

    public Ubication getUbication() {
        return ubication;
    }

    public String getDescription() {
        return description;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {

        return filename;
    }

}
