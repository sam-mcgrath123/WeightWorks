package fragments.workout;

import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapters.HistoryAdapter;
import adapters.WorkoutExerciseAdapter;
import objects.Exercise;
import objects.Set;
import objects.Workout;

public class NewWorkoutFragment extends Fragment {

    ArrayList<Exercise> exercises;
    ArrayList<Set> sets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_workout, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initializeSets();
        initializeExercises();
        RecyclerView rvExercises = view.findViewById(R.id.new_workout_rv_exercises);

        WorkoutExerciseAdapter workoutExerciseAdapter = new WorkoutExerciseAdapter(exercises);
        rvExercises.setAdapter(workoutExerciseAdapter);
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
        Exercise exercise1 = new Exercise("Bench Press", "Chest", sets);
        initializeSets();
        Exercise exercise2 = new Exercise("Deadlift", "Back", sets);
        initializeSets();
        Exercise exercise3 = new Exercise("Squat", "Legs", sets);
        initializeSets();
        Exercise exercise4 = new Exercise("Squat", "Legs", sets);
        initializeSets();
        Exercise exercise5 = new Exercise("Squat", "Legs", sets);
        exercises.add(exercise1);
        exercises.add(exercise2);
        exercises.add(exercise3);
        exercises.add(exercise4);
        exercises.add(exercise5);
    }
}