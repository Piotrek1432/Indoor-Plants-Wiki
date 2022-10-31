package com.piotrjankowski.polsl.indoor_plants_wiki.logic;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.CategoryRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.CategoryReadModel;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.CategoryWriteModel;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequestScope
public class CategoryPlantService {
    private CategoryRepository repository;

    CategoryPlantService(final CategoryRepository repository){
        this.repository = repository;
    }

    public CategoryReadModel createCategory(CategoryWriteModel source){
        Category result = repository.save(source.toCategory());
        return new CategoryReadModel(result);
    }

    public List<CategoryReadModel> readAllCategories(){
        return repository.findAll().stream()
                .map(CategoryReadModel::new).collect(Collectors.toList());
    }

    public void changeDescription(int categoryId, String newDesc){
        Category result = repository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Category with ID: " + String.valueOf(categoryId) + " does not exist"));
        result.setDescription(newDesc);
        repository.save(result);
    }
}
