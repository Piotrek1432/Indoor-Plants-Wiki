package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;

public class CategoryWriteModel {
    private String name;
    private String description;

//    private Set<CategoryPlantWriteModel> plants;
//    private final PlantRepository plantRepository;
//
//    public CategoryWriteModel(PlantRepository plantRepository) {
//        this.plantRepository = plantRepository;
//    }

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

//    public Set<CategoryPlantWriteModel> getPlants() {
//        return plants;
//    }
//
//    public void setPlants(Set<CategoryPlantWriteModel> plants) {
//        this.plants = plants;
//    }

    public Category toCategory(User author){
        Category result = new Category();
        result.setName(name);
        result.setDescription(description);
        result.setAuthor(author);
//        result.setAssignedPlants(
//                plants.stream()
//                        .map(source -> source.toPlant(result))
//                        .collect(Collectors.toSet())
//        );
        return result;
    }
}
