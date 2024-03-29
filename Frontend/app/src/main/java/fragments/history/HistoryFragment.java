package fragments.history;

import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapters.HistoryAdapter;
import objects.Set;
import objects.Workout;
import objects.Exercise;

public class HistoryFragment extends Fragment {

    ArrayList<Set> sets;
    ArrayList<Exercise> exercises;
    ArrayList<Workout> workouts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initializeExercises();
        initializeWorkouts();
        RecyclerView rvExercises = (RecyclerView) view.findViewById(R.id.history_rv);

        HistoryAdapter historyAdapter = new HistoryAdapter(workouts);
        rvExercises.setAdapter(historyAdapter);
        rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initializeSets() {
        sets = new ArrayList<>();
        Set set1 = new Set(1, "90 lb x 12");
        Set set2 = new Set(2, "90 lb x 12");
        Set set3 = new Set(3, "90 lb x 12");
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);
    }

    private void initializeExercises() {
        exercises = new ArrayList<>();
        initializeSets();
        Exercise exercise1 = new Exercise("Bench Press", "Chest", sets);
        initializeSets();
        Exercise exercise2 = new Exercise("Deadlift", "Back", sets);
        initializeSets();
        Exercise exercise3 = new Exercise("Squat", "Legs", sets);
        exercises.add(exercise1);
        exercises.add(exercise2);
        exercises.add(exercise3);
    }

    private void initializeWorkouts() {
        workouts = new ArrayList<>();
        Workout workout1 = new Workout("Push Workout", "1/10/2022", "60min", "1,000lbs", "0Prs", exercises);
        initializeExercises();
        Workout workout2 = new Workout("Pull Workout", "10/10/2022", "55min", "15,000lbs", "5Prs", exercises);
        initializeExercises();
        Workout workout3 = new Workout("Legs Workout", "12/10/2022", "120min", "20,000lbs", "10Prs", exercises);
        workouts.add(workout1);
        workouts.add(workout2);
        workouts.add(workout3);
    }
}