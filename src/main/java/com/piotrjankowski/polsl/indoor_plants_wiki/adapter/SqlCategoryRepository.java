package com.piotrjankowski.polsl.indoor_plants_wiki.adapter;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlCategoryRepository extends CategoryRepository,JpaRepository<Category, Integer> {
    @Override
    @Query("from Category c join fetch c.plants")
    List<Category> findAll();
}
