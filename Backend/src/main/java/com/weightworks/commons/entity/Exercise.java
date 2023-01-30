package com.weightworks.commons.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Routine routine;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "exercise")
    private java.util.Set<Set> sets = new HashSet<>();

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public java.util.Set<Set> getSets() {
        return sets;
    }

    public void setSets(java.util.Set<Set> sets) {
        this.sets = sets;

        for (Set s : sets) {
            s.setExercise(this);
        }
    }
}