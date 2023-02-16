package fragments.workout;

import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapters.WorkoutExerciseAdapter;
import objects.Exercise;
import objects.Set;

public class AddRoutineFragment extends Fragment {

    ArrayList<Exercise> exercises;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_routine, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        RecyclerView rvExercises = view.findViewById(R.id.new_routine_rv_exercises);

        Exercise exercise = new Exercise("Bench Press", "Chest");
        Set set1 = new Set(1, "90 lb x 12");
        ArrayList<Set> set = new ArrayList<>();
        set.add(set1);
        exercise.setSets(set);
        exercises = new ArrayList<>();
        exercises.add(exercise);

        WorkoutExerciseAdapter workoutExerciseAdapter = new WorkoutExerciseAdapter(exercises);
        rvExercises.setAdapter(workoutExerciseAdapter);
        rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));
        if (exercises.size() == 0) {
            LinearLayoutCompat cvAddRoutine = view.findViewById(R.id.add_routine_constraint_layout);
            LayoutInflater.from(getContext()).inflate(R.layout.item_workout_buttons, cvAddRoutine, true);
        }
    }
}
