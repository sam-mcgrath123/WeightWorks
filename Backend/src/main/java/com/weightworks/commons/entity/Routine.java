package com.weightworks.commons.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routines")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "mostRecentCompletion")
    private Date mostRecentCompletion;

    @Column(name = "isCustom")
    private boolean isCustom;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "routine")
    private java.util.Set<Exercise> exercises = new HashSet<>();

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

    public Date getMostRecentCompletion() {
        return mostRecentCompletion;
    }

    public void setMostRecentCompletion(Date mostRecentCompletion) {
        this.mostRecentCompletion = mostRecentCompletion;
    }

    @JsonProperty("isCustom")
    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }
}