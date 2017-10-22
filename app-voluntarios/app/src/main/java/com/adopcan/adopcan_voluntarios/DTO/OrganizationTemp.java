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
}
