package com.piotrjankowski.polsl.indoor_plants_wiki.logic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = IllegalExceptionProcrssing.class)
public class IllegalExceptionControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.notFound().build();
    }

}
