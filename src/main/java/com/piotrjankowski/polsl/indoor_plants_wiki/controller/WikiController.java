package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.logic.FileStorageService;
import com.piotrjankowski.polsl.indoor_plants_wiki.logic.WikiService;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChangeRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.FileResponse;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.NewPlantWriteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/wiki")
@CrossOrigin("http://localhost:3000")
public class WikiController {
    public static final Logger logger = LoggerFactory.getLogger(WikiController.class);
    private final PlantRepository plantRepository;
    private final PlantChangeRepository plantChangeRepository;
    private final WikiService service;
    private final FileStorageService fileStorageService;


    public WikiController(PlantRepository plantRepository, PlantChangeRepository plantChangeRepository, WikiService service, FileStorageService fileStorageService){
        this.plantRepository = plantRepository;
        this.plantChangeRepository = plantChangeRepository;
        this.service = service;
        this.fileStorageService = fileStorageService;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/createNewPlant")
    ResponseEntity<?> createNewPlant(
            @AuthenticationPrincipal User user,
            @RequestBody
            NewPlantWriteModel newPlant
    ){
        logger.info("Adding plant: "+newPlant.getName());
        logger.info("Adding plant: "+newPlant.getImageUri());
        if(newPlant.getName() != null && !plantRepository.existsByName(newPlant.getName())){
            service.addNewPlant(newPlant, user);
        }else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT,path = "/addImage/{namePrefix}")
    public ResponseEntity<FileResponse> uploadFile(
            @PathVariable String namePrefix,
            @RequestParam("file")MultipartFile file
            ){
        String fileName = fileStorageService.storeFile(file,namePrefix);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/plants/test/")
                .path(fileName)
                .toUriString();
        FileResponse fileResponse = new FileResponse(fileName,fileDownloadUri,file.getContentType(),file.getSize());
        return new ResponseEntity<FileResponse>(fileResponse, HttpStatus.OK);
    }
}
