package com.adopcan.adopcan_voluntarios.Security;

import com.google.gson.annotations.SerializedName;

/**
 * Created by german on 23/8/2017.
 */

public class ReportTest {

    private String id;

    private String foto;

    private String latitude;

    private String longitude;

    private String descripcion;

    private int estado;

    @SerializedName("created_at")
    private createdAt createdAt;


    public com.adopcan.adopcan_voluntarios.Security.createdAt getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(com.adopcan.adopcan_voluntarios.Security.createdAt createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
