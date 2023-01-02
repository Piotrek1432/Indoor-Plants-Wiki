package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChange;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;

public class NewPlantWriteModel {
    private String name;
    private String description;
    private String imageUri;

    public NewPlantWriteModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public PlantChange toPlantChange(User user) {
        var result = new PlantChange();
        result.setName(name);
        result.setDescription(description);
        result.setImagePath(imageUri);
        result.setAuthor(user);
        result.setAccepted(false);
        result.setRejected(false);
        return result;
    }
}
