package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.logic.FileStorageService;
import com.piotrjankowski.polsl.indoor_plants_wiki.logic.PlantService;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.PlantPreviewModel;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.SignaturesModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/plants")
@CrossOrigin("http://localhost:3000")
public class PlantController {
    private static final Logger logger = LoggerFactory.getLogger(PlantController.class);
    private final PlantRepository repository;
    private final PlantService service;
    private  final FileStorageService fileStorageService;

    public PlantController(PlantRepository repository, PlantService service, FileStorageService fileStorageService) {
        this.repository = repository;
        this.service = service;
        this.fileStorageService = fileStorageService;
    }

    @RequestMapping(method = RequestMethod.GET, params = {"!page","!size","!sort"})
    ResponseEntity<List<PlantPreviewModel>> readAllPlants(){
        logger.info("Exposing all plants!");
        List<Plant> plants = repository.findAll();
        plants.sort(Comparator.comparing(Plant::getName));
        return ResponseEntity.ok(plants.stream().map(PlantPreviewModel::new).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<PlantPreviewModel>> readAllPlants(Pageable page){
        logger.info("Exposing plants, pageable");
        return ResponseEntity.ok(repository.findAll(page).stream().map(PlantPreviewModel::new).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    ResponseEntity<Plant> readPlant(@PathVariable int id){
        logger.info("Exposing all plants!");
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(method = RequestMethod.GET, path = "search/name")
    ResponseEntity<List<Plant>> search(@RequestParam(required = false) String name){
        return ResponseEntity.ok(repository.findByName(name));
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addPlant(
            @RequestBody
            @Valid
            Plant toAdd
    ){
        repository.save(toAdd);

        return ResponseEntity.noContent().build();
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

    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/test")
    public ResponseEntity<Plant> testApi(
            @AuthenticationPrincipal User user
            ){
        Plant plant = service.testSave(user);
        return ResponseEntity.ok(plant);
    }

    @RequestMapping(method = RequestMethod.GET,path = "test/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = fileStorageService.loadFilesResource(fileName);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch(Exception ex){
            System.out.println("Could not determine fileType");
        }

        if(contentType==null){
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @RequestMapping(method = RequestMethod.GET, path="category/{id}", params = {"!page","!size","!sort"})
    ResponseEntity<List<PlantPreviewModel>> readAllPlantsFromCategory(
            @PathVariable
            int id
    ){
        logger.info("Exposing all plants from category!");
        List<Plant> plants = repository.findByCategories_Id(id);
        plants.sort(Comparator.comparing(Plant::getName));
        return ResponseEntity.ok(plants.stream().map(PlantPreviewModel::new).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.GET, path="outOfCategory/{id}", params = {"!page","!size","!sort"})
    ResponseEntity<List<PlantPreviewModel>> readAllPlantsOutOfCategory(
            @PathVariable
            int id
    ){
        logger.info("Exposing all plants from category!");
        List<Plant> plants = repository.findAll();
        List<Plant> plantsInCategory = repository.findByCategories_Id(id);
        plants.removeAll(plantsInCategory);
        plants.sort(Comparator.comparing(Plant::getName));
        return ResponseEntity.ok(plants.stream().map(PlantPreviewModel::new).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.GET, path="readSignatures/{id}")
    ResponseEntity<SignaturesModel> readSignatures(
            @PathVariable
            int id
    ){
        logger.info("reading Signatures!");
        Plant plant = repository.findById(id).orElse(null);
        if(plant==null) ResponseEntity.badRequest().build();
        SignaturesModel signaturesModel = new SignaturesModel(
                plant.getAuthor().getUsername(),
                plant.getCreatedOn(),
                plant.getUpdatedOn()
        );
        return ResponseEntity.ok(signaturesModel);
    }
}
