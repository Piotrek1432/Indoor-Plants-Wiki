package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/comment")
@CrossOrigin("http://localhost:3000")
public class CommentController {
    public static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentRepository repository;

    public CommentController(CommentRepository repository){
        this.repository = repository;
    }
}
