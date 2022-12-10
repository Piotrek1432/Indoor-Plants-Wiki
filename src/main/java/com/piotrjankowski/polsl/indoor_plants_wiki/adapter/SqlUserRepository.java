package com.piotrjankowski.polsl.indoor_plants_wiki.adapter;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer> {

}
