package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RepositoryRestController
public class PlantController {
    private static final Logger logger = LoggerFactory.getLogger(PlantController.class);
    private final PlantRepository repository;

    public PlantController(PlantRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/plants")
    ResponseEntity<?> readAllPlants(){
        logger.info("Exposing all plants!");
        return ResponseEntity.ok(repository.findAll());
    }
}
