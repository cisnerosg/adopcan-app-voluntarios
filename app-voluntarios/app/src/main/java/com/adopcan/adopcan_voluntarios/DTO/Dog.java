package com.adopcan.adopcan_voluntarios.DTO;

/**
 * Created by Alcides on 18/8/2017.
 * Clase que obtiene los datos de los perros cargados.
 */

public class Dog {

    private String foto;
    private String nombre;
    private String edad;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
