package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

import java.time.LocalDateTime;

public class SignaturesModel {
    private String authorName;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public SignaturesModel(String name, LocalDateTime createdOn, LocalDateTime updatedOn){
        this.authorName = name;
        this.createdOn = createdOn;
        if(updatedOn==null)this.updatedOn=createdOn;
        else this.updatedOn = updatedOn;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
