package com.adopcan.adopcan_voluntarios.DTO;

import android.graphics.Bitmap;

/**
 * Created by german on 16/8/2017.
 * lo creo temporalmente, despues unifico con el q use Alcides
 */

public class DogTemp extends Dog {

    private long id;
    private String name;
    private Bitmap photo;

    public DogTemp(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
