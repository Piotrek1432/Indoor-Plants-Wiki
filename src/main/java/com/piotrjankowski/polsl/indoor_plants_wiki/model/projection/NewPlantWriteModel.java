package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChange;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;

public class NewPlantWriteModel {
    private String name;
    private String description;

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

    public PlantChange toPlantChange(User user) {
        var result = new PlantChange();
        result.setName(name);
        result.setDescription(description);
        result.setAuthor(user);
        result.setAccepted(false);
        return result;
    }
}
