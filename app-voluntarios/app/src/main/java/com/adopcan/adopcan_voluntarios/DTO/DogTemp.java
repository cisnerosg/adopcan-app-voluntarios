package com.adopcan.adopcan_voluntarios.DTO;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by german on 16/8/2017.
 * lo creo temporalmente, despues unifico con el q use Alcides
 */

public class DogTemp implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("nombre")
    private String name;
    @SerializedName("sexo")
    private String sex;
    @SerializedName("edad")
    private String edad;
    @SerializedName("estadp")
    private String state;

    private Bitmap photo;

    public DogTemp(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
