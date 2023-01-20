package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plants")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotBlank(message = "Plant name cannot be empty")
    private String name;
    @Column(length = 600)
    private String description;
    @Column(length = 500)
    private String positiveQualities;
    @Column(length = 300)
    private String insolation;
    @Column(length = 300)
    private String watering;
    @Column(length = 500)
    private String fertilization;
    @Column(length = 500)
    private String badSignals;
    private String imagePath;
//    @Embedded
//    private Audit audit = new Audit();
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    @ManyToMany(mappedBy = "assignedPlants")
    private Set<Category> categories = new HashSet<>();
    @OneToMany(mappedBy = "plant")
    @JsonIgnore
    private Set<PlantChange> plantChanges;
    @OneToMany(mappedBy = "plant")
    @JsonIgnore
    private Set<Comment> comments;
    @ManyToOne
    private User author;

    public Plant() {
    }

    public Plant(String name, String description) {
        this(name,description,null);
    }

    public Plant(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        categories.add(category);
    }


    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateFrom(final Plant toUpdate){
        name = toUpdate.name;
        description = toUpdate.description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPositiveQualities() {
        return positiveQualities;
    }

    public void setPositiveQualities(String positiveQualities) {
        this.positiveQualities = positiveQualities;
    }

    public String getInsolation() {
        return insolation;
    }

    public void setInsolation(String insolation) {
        this.insolation = insolation;
    }

    public String getWatering() {
        return watering;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public String getFertilization() {
        return fertilization;
    }

    public void setFertilization(String fertilization) {
        this.fertilization = fertilization;
    }

    public String getBadSignals() {
        return badSignals;
    }

    public void setBadSignals(String badSignals) {
        this.badSignals = badSignals;
    }

    public Set<PlantChange> getPlantChanges() {
        return plantChanges;
    }

    public void setPlantChanges(Set<PlantChange> plantChanges) {
        this.plantChanges = plantChanges;
    }

    @PrePersist//called before the entry is created on the database
    void prePersist(){
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void preMarge(){
        updatedOn = LocalDateTime.now();
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
