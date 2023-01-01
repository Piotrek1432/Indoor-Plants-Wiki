package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PlantRepository {
    List<Plant> findAll();
    Page<Plant> findAll(Pageable page);
    Optional<Plant> findById(Integer id);
    List<Plant> findByName(String name);
    boolean existsById(Integer id);
    Plant save(Plant entity);
    boolean existsByName(String name);
}
