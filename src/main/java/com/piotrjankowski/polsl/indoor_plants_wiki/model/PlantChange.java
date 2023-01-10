package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import javax.persistence.*;

@Entity
@Table(name = "plants_changes")
public class PlantChange {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
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
    private boolean accepted;
    private boolean rejected;
    @Embedded
    private Audit audit = new Audit();
    @ManyToOne
    private Plant plant;
    @ManyToOne
    private User author;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
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

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
