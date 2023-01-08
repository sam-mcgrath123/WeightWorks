package objects;

import java.util.ArrayList;

public class Exercise {

    private String name;

    private String type;

    private String set;

    private ArrayList<Set> sets;

    public Exercise() {
    }

    public Exercise(String name, String type, String set) {
        this.name = name;
        this.type = type;
        this.set = set;
    }

    public Exercise(String name, String type, ArrayList<Set> sets) {
        this.name = name;
        this.type = type;
        this.sets = sets;
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

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    //TODO
    public String getBestSet() {
        return set;
    }

    public ArrayList<Set> getSets() {
        return sets;
    }

    public void setSets(ArrayList<Set> sets) {
        this.sets = sets;
    }
}
