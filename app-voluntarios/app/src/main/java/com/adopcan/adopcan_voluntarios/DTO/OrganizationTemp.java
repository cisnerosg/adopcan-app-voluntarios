package com.adopcan.adopcan_voluntarios.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aurelio on 17/10/2017.
 */

public class OrganizationTemp  implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("nombre")
    private String name;
    @SerializedName("direccion")
    private String address;
    @SerializedName("telefono")
    private String telephone;
    @SerializedName("email")
    private String mail;
    @SerializedName("web")
    private String web;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
