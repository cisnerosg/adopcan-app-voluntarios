package com.adopcan.adopcan_voluntarios.DTO;

import java.io.Serializable;

/**
 * Created by german on 12/8/2017.
 */

public class Ubication implements Serializable{

    private double latitud;
    private double longitud;

    public Ubication(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
