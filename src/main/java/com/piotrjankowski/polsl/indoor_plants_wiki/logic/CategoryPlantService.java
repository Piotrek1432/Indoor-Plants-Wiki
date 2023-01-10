package com.piotrjankowski.polsl.indoor_plants_wiki.logic;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.*;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.CategoryReadModel;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.CategoryWriteModel;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.stream.Collectors;


public class CategoryPlantService {
    private CategoryRepository repository;
    private PlantRepository plantRepository;


    CategoryPlantService(final CategoryRepository repository, PlantRepository plantRepository){
        this.repository = repository;
        this.plantRepository = plantRepository;
    }

    public CategoryReadModel createCategory(CategoryWriteModel source, User author){
        Category result = repository.save(source.toCategory(author));
        //result.getAssignedPlants().forEach(plant -> plantRepository.save(plant));
        return new CategoryReadModel(result);
    }

    public void addToCategory(int plantId, int categoryId){
        Category category = repository.findById(categoryId).orElse(null);
        Plant plant = plantRepository.findById(plantId).orElse(null);
        if(category!=null){
            category.addSinglePlant(plant);
            repository.save(category);
        }
    }

    public void deleteToCategory(int plantId, int categoryId){
        Category category = repository.findById(categoryId).orElse(null);
        Plant plant = plantRepository.findById(plantId).orElse(null);
        if(category!=null){
            category.removeSinglePlant(plant);
            repository.save(category);
        }
    }

    public List<CategoryReadModel> readAllCategories(){
        return repository.findAll().stream()
                .map(CategoryReadModel::new).collect(Collectors.toList());
    }

    public void changeDescription(int categoryId, String newDesc){
        Category result = repository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category with ID: " + String.valueOf(categoryId) + " does not exist"));
        result.setDescription(newDesc);
        repository.save(result);
    }
}
