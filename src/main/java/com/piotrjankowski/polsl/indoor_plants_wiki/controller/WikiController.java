package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.logic.WikiService;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChangeRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.NewPlantWriteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/wiki")
@CrossOrigin("http://localhost:3000")
public class WikiController {
    public static final Logger logger = LoggerFactory.getLogger(WikiController.class);
    private final PlantRepository plantRepository;
    private final PlantChangeRepository plantChangeRepository;
    private final WikiService service;


    public WikiController(PlantRepository plantRepository, PlantChangeRepository plantChangeRepository, WikiService service){
        this.plantRepository = plantRepository;
        this.plantChangeRepository = plantChangeRepository;
        this.service = service;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/createNewPlant")
    ResponseEntity<?> createNewPlant(
            @AuthenticationPrincipal User user,
            @RequestBody
            NewPlantWriteModel newPlant
    ){
        if(newPlant.getName() != null && !plantRepository.existsByName(newPlant.getName())){
            service.addNewPlant(newPlant, user);
        }else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
