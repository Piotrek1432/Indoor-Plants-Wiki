package com.piotrjankowski.polsl.indoor_plants_wiki.logic;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChangeRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.NewPlantWriteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WikiService {
    public static final Logger logger = LoggerFactory.getLogger(WikiService.class);

    private PlantChangeRepository plantChangeRepository;

    public WikiService(final PlantChangeRepository plantChangeRepository) {
        this.plantChangeRepository = plantChangeRepository;
    }

    public void addNewPlant(NewPlantWriteModel newPlantWriteModel, User author){
        plantChangeRepository.save(newPlantWriteModel.toPlantChange(author));
    }
}
