package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import java.util.List;

public interface PlantChangeRepository {
    PlantChange save(PlantChange plantChangeToSave);
    List<PlantChange> findAll();
    PlantChange findById(int id);
    List<PlantChange> findAllByAcceptedIsFalseAndRejectedIsFalse();
}
