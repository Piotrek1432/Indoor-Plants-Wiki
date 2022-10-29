package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotBlank(message = "Category name cannot be empty")
    private String name;
    private String description;
    @Embedded
    private Audit audit = new Audit();
    @OneToMany(mappedBy = "category")
    private Set<Plant> plants;

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

    public Set<Plant> getPlants() {
        return plants;
    }

    public void setPlants(Set<Plant> plants) {
        this.plants = plants;
    }
}
