package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlantController {
    private static final Logger logger = LoggerFactory.getLogger(PlantController.class);
    private final PlantRepository repository;

    public PlantController(PlantRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/plants", params = {"!page","!size","!sort"})
    ResponseEntity<List<Plant>> readAllPlants(){
        logger.info("Exposing all plants!");
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/plants")
    ResponseEntity<List<Plant>> readAllPlants(Pageable page){
        logger.info("Exposing plants, pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/plants/{id}")
    ResponseEntity<Plant> readPlant(@PathVariable int id){
        logger.info("");
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, path = "/plants/{id}")
    ResponseEntity<?> updatePlant(
            @PathVariable
            int id,

            @RequestBody
            @Valid
            Plant toUpdate
    ){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repository.findById(id).ifPresent(plant -> {
            plant.updateFrom(toUpdate);
            repository.save(plant);
        });

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PATCH, path = "/plants/{id}")
    public ResponseEntity<?> patchPlant(
            @PathVariable
            int id
    ){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repository.findById(id).ifPresent(plant -> plant.setDescription("new desc.. test patch"));

        return ResponseEntity.noContent().build();
    }
}
