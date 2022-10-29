package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface sqlCategoryRepository extends CategoryRepository,JpaRepository<Category, Integer> {

}
