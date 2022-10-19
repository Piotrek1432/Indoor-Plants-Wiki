package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource()
public interface PlantRepository extends JpaRepository<Plant, Integer> {
}
