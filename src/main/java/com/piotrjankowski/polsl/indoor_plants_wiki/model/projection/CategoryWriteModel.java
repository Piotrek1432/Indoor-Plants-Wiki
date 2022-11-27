package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryWriteModel {
    private String name;
    private String description;

    private Set<CategoryPlantWriteModel> plants;

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

    public Set<CategoryPlantWriteModel> getPlants() {
        return plants;
    }

    public void setPlants(Set<CategoryPlantWriteModel> plants) {
        this.plants = plants;
    }

    public Category toCategory(){
        var result = new Category();
        result.setName(name);
        result.setDescription(description);
        result.setPlants(
                plants.stream()
                        .map(source -> source.toPlant(result))
                        .collect(Collectors.toSet())
        );
        return result;
    }
}
