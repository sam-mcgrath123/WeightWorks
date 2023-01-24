package objects;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Exercise {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    private ArrayList<Set> sets;

    public Exercise() {
    }

    public Exercise(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Exercise(String name, String type, ArrayList<Set> sets) {
        this.name = name;
        this.type = type;
        this.sets = sets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getBestSet() {
        if(sets == null) {
            return "";
        }
        Set bestSet = sets.get(0);
        for(Set set : sets) {
            if(set.getWeight() > bestSet.getWeight()) {
                bestSet = set;
            }
        }
        return bestSet.toString();
    }

    public ArrayList<Set> getSets() {
        return sets;
    }

    public void setSets(ArrayList<Set> sets) {
        this.sets = sets;
    }
}
