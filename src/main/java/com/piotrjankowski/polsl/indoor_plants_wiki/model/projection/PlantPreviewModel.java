package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;

public class PlantPreviewModel {
    private int id;
    private String name;
    private String description;
    private String imagePath;

    public PlantPreviewModel(Plant plant){
        id = plant.getId();
        name = plant.getName();
        description = plant.getDescription();
        imagePath = plant.getImagePath();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
