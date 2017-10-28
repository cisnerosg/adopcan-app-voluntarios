package com.adopcan.adopcan_voluntarios.DTO;

/**
 * Created by german on 20/8/2017.
 */

public enum  UserType {

    ORGANIZATION(1,"organizacion"),SIMPLE(2,"simple"),VOLUNTARY(3,"voluntario");

    public int id;
    public String description;

    UserType(int id, String description){
        this.id = id;
        this.description = description;
    }

    public static UserType getById(int id){
        for (UserType type: UserType.values()){
            if(type.getId() == id){
                return type;
            }
        }

        throw new IllegalArgumentException("El tipo buscado no existe");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
