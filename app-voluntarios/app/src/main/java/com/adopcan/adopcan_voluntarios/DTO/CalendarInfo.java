package com.adopcan.adopcan_voluntarios.DTO;

import java.util.Date;

/**
 * Created by german on 16/8/2017.
 */

public class CalendarInfo {

    private Long id;
    private DogTemp dog;
    private String place;
    private Date eventDate;
    private Date eventHour;
    private String description;



    public CalendarInfo(Long id, Date eventDate,  Date eventHour, String description, DogTemp dog, String place) {
        this.id = id;
        this.eventDate = eventDate;
        this.description = description;
        this.dog = dog;
        this.eventHour = eventHour;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getEventHour() {
        return eventHour;
    }

    public void setEventHour(Date eventHour) {
        this.eventHour = eventHour;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
