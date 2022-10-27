package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
class Audit {
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;


    @PrePersist//called before the entry is created on the database
    void prePersist(){
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void preMarge(){
        updatedOn = LocalDateTime.now();
    }
}
