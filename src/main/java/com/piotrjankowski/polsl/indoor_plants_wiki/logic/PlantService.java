package com.piotrjankowski.polsl.indoor_plants_wiki.logic;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PlantService {
    public static final Logger logger = LoggerFactory.getLogger(PlantService.class);
    private final PlantRepository repository;

    public PlantService(PlantRepository repository) {
        this.repository = repository;
    }

    @Async
    public CompletableFuture<List<Plant>> findAllAsync(){
        logger.info("Supply async");
        return CompletableFuture.supplyAsync(repository::findAll);
    }

    public Plant testSave(User user)
    {
        Plant plant = new Plant();
        plant.setName("testowa roslina");
        plant.setDescription("...");
        return repository.save(plant);
    }
}
