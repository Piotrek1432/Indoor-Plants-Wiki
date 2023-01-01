package com.piotrjankowski.polsl.indoor_plants_wiki.controller;


import com.piotrjankowski.polsl.indoor_plants_wiki.logic.WikiService;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChange;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin("http://localhost:3000")
public class AdministrationController {
    public static final Logger logger = LoggerFactory.getLogger(AdministrationController.class);

    private PlantChangeRepository plantChangeRepository;

    private final WikiService service;

    public AdministrationController(PlantChangeRepository plantChangeRepository, WikiService service) {
        this.plantChangeRepository = plantChangeRepository;
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAllPlantChangesToAccept")
    ResponseEntity<List<PlantChange>> getAllPlantChangesToAccept(){
        logger.info("Reading all changes to accept");

        return ResponseEntity.ok(plantChangeRepository.findAll());
    }
}
