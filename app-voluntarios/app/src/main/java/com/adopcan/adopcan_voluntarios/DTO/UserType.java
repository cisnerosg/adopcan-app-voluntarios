package com.adopcan.adopcan_voluntarios.DTO;

/**
 * Created by german on 20/8/2017.
 */

public enum  UserType {

    VOLUNTARY(1L,"voluntario"),SIMPLE(2L,"simple");

    public Long id;
    public String description;

    UserType(Long id, String description){
        this.id = id;
        this.description = description;
    }

    public static UserType getById(Long id){
        for (UserType type: UserType.values()){
            if(type.getId().equals(id)){
                return type;
            }
        }

        throw new IllegalArgumentException("El tipo buscado no existe");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
