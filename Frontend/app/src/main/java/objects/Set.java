package objects;

public class Set {

    private int setId;

    private String previous;

    private int weight;

    private int reps;

    private boolean finished;

    public Set() {
    }

    public Set(int setId, String previous, int weight, int reps, boolean finished) {
        this.setId = setId;
        this.previous = previous;
        this.weight = weight;
        this.reps = reps;
        this.finished = finished;
    }

    public Set(int setId, String previous) {
        this.setId = setId;
        this.previous = previous;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return weight + "lb x " + reps;
    }
}
