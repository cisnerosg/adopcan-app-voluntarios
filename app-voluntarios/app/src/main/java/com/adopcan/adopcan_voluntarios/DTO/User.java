package com.adopcan.adopcan_voluntarios.DTO;

import android.media.session.MediaSession;

import com.adopcan.adopcan_voluntarios.Security.ResponseToken;

/**
 * Created by german on 20/8/2017.
 */

public class User {

    private String username;
    private String password;
    private UserType type;
    private ResponseToken responseToken;


    public User(String username, String password, UserType type) {
        this.username = username;
        this.password = password;
        this.type = type;

    }

    public void addResponseToken(ResponseToken responseToken){
        this.responseToken = responseToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
