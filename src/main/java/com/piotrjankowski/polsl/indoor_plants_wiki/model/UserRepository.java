package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    Optional<User> findById(Integer id);
    User save(User userToSave);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
