package com.adopcan.adopcan_voluntarios.DTO;

import java.util.Date;

/**
 * Created by german on 16/8/2017.
 */

public class CalendarInfo {

    private long id;
    private Date eventDate;
    private String description;
    private DogTemp dog;

    public CalendarInfo(long id, Date eventDate, String description, DogTemp dog) {
        this.id = id;
        this.eventDate = eventDate;
        this.description = description;
        this.dog = dog;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DogTemp getDog() {
        return dog;
    }

    public void setDog(DogTemp dog) {
        this.dog = dog;
    }
}
