package com.adopcan.adopcan_voluntarios.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aurelio on 17/10/2017.
 */

public class OrganizationTemp  implements Serializable {

    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }
}
