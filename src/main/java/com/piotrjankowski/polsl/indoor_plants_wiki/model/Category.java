package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotBlank(message = "Category name cannot be empty")
    private String name;
    private String description;
    @Embedded
    private Audit audit = new Audit();
    @ManyToMany
    private Set<Plant> assignedPlants = new HashSet<>();

    @ManyToOne
    private User author;

    public Category(){

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

    public void updateFrom(final Category toUpdate){
        name = toUpdate.name;
        description = toUpdate.description;
    }

    public Set<Plant> getAssignedPlants() {
        return assignedPlants;
    }

    public void setAssignedPlants(Set<Plant> assignedPlants) {
        this.assignedPlants = assignedPlants;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void addSinglePlant(Plant plant) {
        this.assignedPlants.add(plant);
    }
}
