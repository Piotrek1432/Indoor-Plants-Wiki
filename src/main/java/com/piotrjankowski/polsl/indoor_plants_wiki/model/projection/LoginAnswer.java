package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

public class LoginAnswer {
    private String answer;
    private String role;

    public LoginAnswer(){}

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
