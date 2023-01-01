package com.piotrjankowski.polsl.indoor_plants_wiki.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotBlank
    private String username;
    @NotBlank
    @JsonIgnore
    private String password;
    private String role;
    @Embedded
    @JsonIgnore
    private Audit audit = new Audit();
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Plant> plants;
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<PlantChange> plantsChanges;
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Category> categories;
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Comment> comments;

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<PlantChange> getPlantsChanges() {
        return plantsChanges;
    }

    public void setPlantsChanges(Set<PlantChange> plantsChanges) {
        this.plantsChanges = plantsChanges;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
