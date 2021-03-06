package com.adopcan.adopcan_voluntarios.DTO;

import android.media.session.MediaSession;

import com.adopcan.adopcan_voluntarios.Security.ResponseToken;

/**
 * Created by german on 20/8/2017.
 */

public class User {

    private String username;
    private String password;
    private ResponseToken responseToken;
    private String facebookToken;
    private OrganizationTemp organization;
    private Account account;
    private int countOrganization =1;

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public void addResponseToken(ResponseToken responseToken){
        this.responseToken = responseToken;
    }

    public void addFacebookToken(String token){
        this.facebookToken = token;
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

    public ResponseToken getResponseToken() {
        return responseToken;
    }

    public OrganizationTemp getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationTemp organization) {
        this.organization = organization;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public int getCountOrganization() {
        return countOrganization;
    }

    public void setCountOrganization(int countOrganization) {
        this.countOrganization = countOrganization;
    }
}
