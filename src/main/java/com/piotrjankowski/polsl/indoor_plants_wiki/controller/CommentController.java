package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.*;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.projection.NewCmmentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(path = "/comment")
@CrossOrigin("http://localhost:3000")
public class CommentController {
    public static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentRepository repository;
    private final PlantRepository plantRepository;

    public CommentController(CommentRepository repository, PlantRepository plantRepository){
        this.repository = repository;
        this.plantRepository = plantRepository;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, path = "/addNewComment/{plantId}")
    ResponseEntity<?> addNewComment(
            @AuthenticationPrincipal User user,
            @PathVariable
            int plantId,
            @RequestBody
            NewCmmentModel newComment
    ){
        logger.info("Adding comment: "+newComment.getComment());
        repository.save(newComment.toComment(user,plantRepository.findById(plantId).orElse(null)));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, path="readAllComments/{plantId}", params = {"!page","!size","!sort"})
    ResponseEntity<List<Comment>> readAllComments(
            @PathVariable
            int plantId
    ){
        logger.info("Exposing all comments!");
        List<Comment> comments = repository.findAllByPlant(plantRepository.findById(plantId).orElse(null));
        comments.sort(Comparator.comparing(Comment::getCreatedOn).reversed());
        return ResponseEntity.ok(comments);
    }
}
