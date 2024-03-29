package com.piotrjankowski.polsl.indoor_plants_wiki.controller;


import com.piotrjankowski.polsl.indoor_plants_wiki.logic.AdministrationService;
import com.piotrjankowski.polsl.indoor_plants_wiki.logic.WikiService;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChange;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChangeRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/administration")
@CrossOrigin("http://localhost:3000")
public class AdministrationController {
    public static final Logger logger = LoggerFactory.getLogger(AdministrationController.class);

    private final PlantChangeRepository plantChangeRepository;
    private final PlantRepository plantRepository;

    private final WikiService service;

    private final AdministrationService administrationService;

    public AdministrationController(PlantChangeRepository plantChangeRepository, PlantRepository plantRepository, WikiService service, AdministrationService administrationService) {
        this.plantChangeRepository = plantChangeRepository;
        this.plantRepository = plantRepository;
        this.service = service;
        this.administrationService = administrationService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAllPlantChangesToAccept")
    ResponseEntity<List<PlantChange>> getAllPlantChangesToAccept(){
        logger.info("Reading all changes to accept");

        return ResponseEntity.ok(plantChangeRepository.findAllByAcceptedIsFalseAndRejectedIsFalse());
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PATCH, path="/acceptNewPlant/{id}")
    ResponseEntity<?> acceptNewPlant(
            @PathVariable
            int id
    ){
        logger.info("PlantChange id: "+id);
        if(plantRepository.existsByName(plantChangeRepository.findById(id).getName()))
        {
            if(plantChangeRepository.findById(id).getPlant()==null) return ResponseEntity.badRequest().build();
        }
        administrationService.newPlantSave(id);

        return ResponseEntity.ok().build();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PATCH, path="/rejectNewPlant/{id}")
    ResponseEntity<?> rejectNewPlant(
            @PathVariable
            int id
    ){
        logger.info("PlantChange id: "+id);
        administrationService.newPlantReject(id);

        return ResponseEntity.ok().build();
    }
}
