package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.logic.CategoryPlantService;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.CategoryRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.CategoryPlantWriteModel;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.CategoryReadModel;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.CategoryWriteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@CrossOrigin("http://localhost:3000")
public class CategoryController {
    public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryRepository repository;
    private final CategoryPlantService service;

    public CategoryController(CategoryRepository repository, CategoryPlantService service) {
        this.repository = repository;
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<CategoryReadModel> createNewCategory(
            @RequestBody
            @Valid
            CategoryWriteModel newCategory
    ){
        logger.info("Adding new category!");

        return ResponseEntity.created(URI.create("/")).body(service.createCategory(newCategory));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"!page","!size","!sort"})
    ResponseEntity<List<CategoryReadModel>> readAllCategories(){
        logger.info("Exposing all categories!");
        return ResponseEntity.ok(service.readAllCategories());
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "{id}")
    ResponseEntity<?> changeDescription(
            @PathVariable
            int id,
            @RequestBody
            @Valid
            String newDesc){
        service.changeDescription(id,newDesc);
        return  ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.notFound().build();
    }
}
