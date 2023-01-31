package com.weightworks.commons.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_completed")
    private Date dateCompleted;

    @Column(name = "time_duration")
    private int timeDuration;

    @Column(name = "total_weight")
    private int totalWeight;

    @Column(name = "number_of_prs")
    private int numberOfPrs;

    @Column(name="isFinished")
    private boolean isFinished;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "workout")
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

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(int timeDuration) {
        this.timeDuration = timeDuration;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getNumberOfPrs() {
        return numberOfPrs;
    }

    public void setNumberOfPrs(int numberOfPrs) {
        this.numberOfPrs = numberOfPrs;
    }

    @JsonProperty("isFinished")
    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }
}