package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin("http://localhost:3000")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(PlantController.class);
    public final UserRepository repository;

    public UserController(UserRepository repository){
        this.repository = repository;
    }


}
