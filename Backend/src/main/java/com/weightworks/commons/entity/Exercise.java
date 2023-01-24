package com.weightworks.commons.entity;


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
