package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;

public class CategoryPlantWriteModel {
    private String name;
    private String description;

    public CategoryPlantWriteModel(Plant source){
        name = source.getName();
        description = source.getDescription();
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

    public Plant toPlant(final Category category){
        return new Plant(name, description, category);
    }
}
