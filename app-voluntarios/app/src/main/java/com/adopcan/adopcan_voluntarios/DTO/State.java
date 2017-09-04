package com.adopcan.adopcan_voluntarios.DTO;

/**
 * Created by german on 20/8/2017.
 */

public enum State {

    ACTIVE(1,"Activo"),INACTIVE(2,"Inactivo"),RESCUED(3,"Rescatado");

    public Integer id;
    public String description;

    State(Integer id, String description){
        this.id = id;
        this.description = description;
    }

    public static State getById(Integer id){
        for (State type: State.values()){
            if(type.getId().equals(id)){
                return type;
            }
        }

        throw new IllegalArgumentException("El estado buscado no existe");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
