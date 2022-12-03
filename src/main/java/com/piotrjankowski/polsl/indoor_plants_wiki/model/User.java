package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotBlank
    private String name;
    @Embedded
    private Audit audit = new Audit();

    @OneToMany(mappedBy = "author")
    private Set<Plant> plants;

    @OneToMany(mappedBy = "author")
    private Set<Category> categories;

    @OneToMany(mappedBy = "author")
    private Set<Comment> comments;

    public User(){

    }

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

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    public void setPlants(Set<Plant> plants) {
        this.plants = plants;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
