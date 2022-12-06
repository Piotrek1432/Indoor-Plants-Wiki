package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(Integer id);
    User save(User userToSave);
    Optional<User> findByUsername(String username);
}
