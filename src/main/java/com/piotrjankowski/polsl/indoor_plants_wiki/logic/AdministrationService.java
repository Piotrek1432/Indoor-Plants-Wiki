package com.piotrjankowski.polsl.indoor_plants_wiki.logic;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChange;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChangeRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AdministrationService {
    public static final Logger logger = LoggerFactory.getLogger(AdministrationService.class);

    private final PlantRepository plantRepository;
    private final PlantChangeRepository plantChangeRepository;

    public AdministrationService(PlantRepository plantRepository, PlantChangeRepository plantChangeRepository) {
        this.plantRepository = plantRepository;
        this.plantChangeRepository = plantChangeRepository;
    }

    public void newPlantSave(int id){
        PlantChange plantChange = plantChangeRepository.findById(id);
        Plant plant = new Plant();
        if(plantChange.getPlant()!=null){
            plant = plantChange.getPlant();
        }
        plant.setAuthor(plantChange.getAuthor());
        plant.setName(plantChange.getName());
        plant.setDescription(plantChange.getDescription());
        plant.setPositiveQualities(plantChange.getPositiveQualities());
        plant.setInsolation(plantChange.getInsolation());
        plant.setWatering(plantChange.getWatering());
        plant.setFertilization(plantChange.getFertilization());
        plant.setBadSignals(plantChange.getBadSignals());
        plant.setImagePath(plantChange.getImagePath());

        plantRepository.save(plant);
        plantChange.setAccepted(true);
        plantChangeRepository.save(plantChange);
    }

    public void newPlantReject(int id){
        PlantChange plantChange = plantChangeRepository.findById(id);
        plantChange.setRejected(true);
        plantChangeRepository.save(plantChange);
    }
}
