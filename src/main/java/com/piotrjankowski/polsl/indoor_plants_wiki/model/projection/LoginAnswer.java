package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

public class LoginAnswer {
    private String jwt;
    private String role;

    public LoginAnswer(){}

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
