package com.piotrjankowski.polsl.indoor_plants_wiki.model.projection;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChange;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;

import javax.persistence.Column;

public class NewPlantWriteModel {
    private String name;
    private String description;
    private String positiveQualities;
    private String insolation;
    private String watering;
    private String fertilization;
    private String badSignals;
    private String imageUri;

    public NewPlantWriteModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public PlantChange toPlantChange(User user) {
        var result = new PlantChange();
        result.setName(name);
        result.setDescription(description);
        result.setPositiveQualities(positiveQualities);
        result.setInsolation(insolation);
        result.setWatering(watering);
        result.setFertilization(fertilization);
        result.setBadSignals(badSignals);
        result.setImagePath(imageUri);
        result.setAuthor(user);
        result.setAccepted(false);
        result.setRejected(false);
        return result;
    }

    public PlantChange toPlantChange(User user, Plant plant) {
        var result = toPlantChange(user);
        result.setPlant(plant);
        return result;
    }

    public String getPositiveQualities() {
        return positiveQualities;
    }

    public void setPositiveQualities(String positiveQualities) {
        this.positiveQualities = positiveQualities;
    }

    public String getInsolation() {
        return insolation;
    }

    public void setInsolation(String insolation) {
        this.insolation = insolation;
    }

    public String getWatering() {
        return watering;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public String getFertilization() {
        return fertilization;
    }

    public void setFertilization(String fertilization) {
        this.fertilization = fertilization;
    }

    public String getBadSignals() {
        return badSignals;
    }

    public void setBadSignals(String badSignals) {
        this.badSignals = badSignals;
    }
}
