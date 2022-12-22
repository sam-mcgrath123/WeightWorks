package objects;

import java.util.ArrayList;

public class Routine {

    private String name;

    private String date;

    private ArrayList<String> exercises;

    public Routine() {
    }

    public Routine(String name, String date, ArrayList<String> exercises) {
        this.name = name;
        this.date = date;
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

    public ArrayList<String> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<String> exercises) {
        this.exercises = exercises;
    }
}
