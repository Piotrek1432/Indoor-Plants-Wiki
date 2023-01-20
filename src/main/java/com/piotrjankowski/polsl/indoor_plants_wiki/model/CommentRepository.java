package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    List<Comment> findAll();
    List<Comment> findAllByPlant(Plant plant);
    Optional<Comment> findById(Integer id);
    Comment save(Comment commentToSave);
}
