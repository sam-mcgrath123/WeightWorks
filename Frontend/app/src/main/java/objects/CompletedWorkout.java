package objects;

import java.util.ArrayList;

public class CompletedWorkout {

    private String name;

    private String date;

    private String duration;

    private String totalWeight;

    private String numOfPRs;

    private ArrayList<Exercise> exercises;

    public CompletedWorkout() {
    }

    public CompletedWorkout(String name, String date, String duration, String totalWeight, String numOfPRs, ArrayList<Exercise> exercises) {
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.totalWeight = totalWeight;
        this.numOfPRs = numOfPRs;
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getNumOfPRs() {
        return numOfPRs;
    }

    public void setNumOfPRs(String numOfPRs) {
        this.numOfPRs = numOfPRs;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }
}
