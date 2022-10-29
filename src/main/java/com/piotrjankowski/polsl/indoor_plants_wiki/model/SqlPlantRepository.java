package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface SqlPlantRepository extends PlantRepository, JpaRepository<Plant, Integer> {
    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from PLANTS where ID = ?1")
    boolean existsById(@Param("id") Integer id);
    @Override
    boolean existsByCategory_id(Integer categoryId);
}
