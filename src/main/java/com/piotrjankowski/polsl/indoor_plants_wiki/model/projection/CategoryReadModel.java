package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryReadModel {
    private String name;
    private String description;
    private Set<CategoryPlantReadModel> plants;

    public CategoryReadModel(Category source){
        name = source.getName();
        description = source.getDescription();
        plants = source.getPlants().stream()
                .map(CategoryPlantReadModel::new)
                .collect(Collectors.toSet());
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

    public Set<CategoryPlantReadModel> getPlants() {
        return plants;
    }

    public void setPlants(Set<CategoryPlantReadModel> plants) {
        this.plants = plants;
    }
}
