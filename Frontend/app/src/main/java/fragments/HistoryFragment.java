package fragments;

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

import adapters.ExercisesAdapter;
import adapters.HistoryAdapter;
import objects.CompletedWorkout;
import objects.Exercise;

public class HistoryFragment extends Fragment {

    ArrayList<Exercise> exercises;
    ArrayList<CompletedWorkout> completedWorkouts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initializeExercises();
        initializeCompletedWorkouts();
        RecyclerView rvExercises = (RecyclerView) view.findViewById(R.id.history_rv);

        HistoryAdapter historyAdapter = new HistoryAdapter(completedWorkouts);
        rvExercises.setAdapter(historyAdapter);
        rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initializeExercises() {
        exercises = new ArrayList<>();
        Exercise exercise1 = new Exercise("Bench Press", "Chest", "225 (x10)");
        Exercise exercise2 = new Exercise("Deadlift", "Back", "405 (x8)");
        Exercise exercise3 = new Exercise("Squat", "Legs", "315 (x12)");
        exercises.add(exercise1);
        exercises.add(exercise2);
        exercises.add(exercise3);
    }

    private void initializeCompletedWorkouts() {
        completedWorkouts = new ArrayList<>();
        CompletedWorkout completedWorkout1 = new CompletedWorkout("Push Workout", "1/10/2022", "60min", "1,000lbs", "0Prs", exercises);
        initializeExercises();
        CompletedWorkout completedWorkout2 = new CompletedWorkout("Pull Workout", "10/10/2022", "55min", "15,000lbs", "5Prs", exercises);
        initializeExercises();
        CompletedWorkout completedWorkout3 = new CompletedWorkout("Legs Workout", "12/10/2022", "120min", "20,000lbs", "10Prs", exercises);
        completedWorkouts.add(completedWorkout1);
        completedWorkouts.add(completedWorkout2);
        completedWorkouts.add(completedWorkout3);
        completedWorkout1 = completedWorkouts.get(0);
        completedWorkout1.getExercises().add(new Exercise("Bench Press", "Chest", "225 (x10)"));
    }
}