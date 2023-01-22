package com.weightworks.commons.entity;


import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @OneToMany
    private ArrayList<Set> setList;

    public Exercise() {
    }

    public Exercise(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Set> getSetList() {
        return setList;
    }

    public void setSetList(ArrayList<Set> setList) {
        this.setList = setList;
    }
}