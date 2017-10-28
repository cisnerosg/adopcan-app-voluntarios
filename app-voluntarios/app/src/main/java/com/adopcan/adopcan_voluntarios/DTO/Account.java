package com.adopcan.adopcan_voluntarios.DTO;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by german on 16/8/2017.
 * lo creo temporalmente, despues unifico con el q use Alcides
 */

public class Account implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("email")
    private String email;
    @SerializedName("apellido")
    private String lastname;
    @SerializedName("nombre")
    private String name;
    @SerializedName("tipo_usuario")
    private int userTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public UserType getUserType(){
        return UserType.getById(userTypeId);
    }
}
