package com.weightworks.commons.entity;

import jakarta.persistence.*;

@Entity(name = "Set")
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column
    private int reps;

    @Column
    private int weight;
}