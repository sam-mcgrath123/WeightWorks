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
import objects.Exercise;
import objects.Set;

public class ExerciseSectionFragment extends Fragment {

    ArrayList<Exercise> exercises;
    ArrayList<Set> sets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_layout, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initializeExercises();
        RecyclerView rvExercises = (RecyclerView) view.findViewById(R.id.rvExercises);

        ExercisesAdapter exercisesAdapter = new ExercisesAdapter(exercises);
        rvExercises.setAdapter(exercisesAdapter);
        rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));
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

    private void initializeSets() {
        sets = new ArrayList<>();
        Set set1 = new Set(1, "90 lb x 12");
        Set set2 = new Set(2, "90 lb x 12");
        Set set3 = new Set(3, "90 lb x 12");
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);
    }
}
