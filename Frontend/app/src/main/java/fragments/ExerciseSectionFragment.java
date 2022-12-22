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

public class ExerciseSectionFragment extends Fragment {

    ArrayList<Exercise> exercises;

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
        Exercise exercise1 = new Exercise("Bench Press", "Chest", "225 (x10)");
        Exercise exercise2 = new Exercise("Deadlift", "Back", "405 (x8)");
        Exercise exercise3 = new Exercise("Squat", "Legs", "315 (x12)");
        exercises.add(exercise1);
        exercises.add(exercise2);
        exercises.add(exercise3);
    }
}
