package com.piotrjankowski.polsl.indoor_plants_wiki.adapter;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChange;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantChangeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlPlantChangeRepository extends PlantChangeRepository, JpaRepository<PlantChange,Integer> {
}
