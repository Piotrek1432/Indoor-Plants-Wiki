package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.logic.PlantService;
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
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/plants")
public class PlantController {
    private static final Logger logger = LoggerFactory.getLogger(PlantController.class);
    private final PlantRepository repository;
    private final PlantService service;

    public PlantController(PlantRepository repository, PlantService service) {
        this.repository = repository;
        this.service = service;
    }

    /**
     * Experimental async
     **/
    @RequestMapping(method = RequestMethod.GET, path = "/readAllPlantsAsync", params = {"!page","!size","!sort"})
    CompletableFuture<ResponseEntity<List<Plant>>> readAllPlantsAsync(){
        logger.info("Exposing all plants!");
        return service.findAllAsync().thenApply(ResponseEntity::ok);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"!page","!size","!sort"})
    ResponseEntity<List<Plant>> readAllPlants(){
        logger.info("Exposing all plants!");
        return ResponseEntity.ok(repository.findAll());
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Plant>> readAllPlants(Pageable page){
        logger.info("Exposing plants, pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    ResponseEntity<Plant> readPlant(@PathVariable int id){
        logger.info("");
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(method = RequestMethod.GET, path = "search/name")
    ResponseEntity<List<Plant>> search(@RequestParam(required = false) String name){
        return ResponseEntity.ok(repository.findByName(name));
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
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
    @RequestMapping(method = RequestMethod.PATCH, path = "/{id}")
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
