package com.piotrjankowski.polsl.indoor_plants_wiki.adapter;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface SqlPlantRepository extends PlantRepository, JpaRepository<Plant, Integer> {
    @Override
    boolean existsById(Integer id);
}
