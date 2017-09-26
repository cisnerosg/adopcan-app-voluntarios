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
    @SerializedName("estado")
    private String state;
    @SerializedName("sexo")
    private String sex;
    @SerializedName("edad")
    private String edad;
    @SerializedName("castrado")
    private String castrated;
    @SerializedName("color")
    private String color;
    @SerializedName("necesidades")
    private String needs;
    @SerializedName("dieta")
    private String diet;
    @SerializedName("fechaIngreso")
    private String admissionDate;
    @SerializedName("notas")
    private String notes;
    @SerializedName("foto")
    private String filename;

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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCastrated() {
        return castrated;
    }

    public void setCastrated(String castrated) {
        this.castrated = castrated;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNeeds() {
        return needs;
    }

    public void setNeeds(String needs) {
        this.needs = needs;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
