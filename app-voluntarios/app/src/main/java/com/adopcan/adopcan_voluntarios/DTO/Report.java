package com.adopcan.adopcan_voluntarios.DTO;

import android.content.Context;
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

    @SerializedName("foto")
    private String filename;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("descripcion")
    private String description;

    @SerializedName("estado")
    private int state;

    @SerializedName("created_at")
    private String createdAt;

    private Date date;

    private Bitmap photo;

    private byte[] photoByte;

    private boolean shareFacebook = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public State getEnumState() {
        return State.getById(state);
    }

    public byte[] getPhotoByte() {
        return photoByte;
    }

    public void setPhotoByte(byte[] photoByte) {
        this.photoByte = photoByte;
    }

    public boolean isShareFacebook() {
        return shareFacebook;
    }

    public void setShareFacebook(boolean shareFacebook) {
        this.shareFacebook = shareFacebook;
    }
}
