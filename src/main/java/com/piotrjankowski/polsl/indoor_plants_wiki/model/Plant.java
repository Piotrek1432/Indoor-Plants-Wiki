package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private String description;
    private String imagePath;
    @Embedded
    private Audit audit = new Audit();
    @ManyToMany(mappedBy = "assignedPlants")
    private Set<Category> categories = new HashSet<>();

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
}
