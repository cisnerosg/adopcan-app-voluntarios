package com.adopcan.adopcan_voluntarios.DTO;

import java.io.Serializable;

/**
 * Created by german on 13/8/2017.
 */

public class MessageAlert implements Serializable{

    private String title;
    private String description;

    public MessageAlert(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
