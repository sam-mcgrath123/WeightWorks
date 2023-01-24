package fragments.workout;

import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Remote.ApiService;
import Remote.Network;
import adapters.ExercisesAdapter;
import objects.Exercise;
import objects.Set;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseSectionFragment extends Fragment {

    ApiService apiService;
    ArrayList<Exercise> exercises;
    ArrayList<Set> sets;
    View view;
    ExercisesAdapter exercisesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_section, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.view = view;
        getAllExercises();
        getParentFragmentManager().setFragmentResultListener("ExerciseAdded", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                postNewExercise(result);
            }
        });
    }

    private void postNewExercise(@NonNull Bundle result) {
        apiService = Network.getInstance().create(ApiService.class);

        Exercise exercise = new Exercise(result.getString("ExerciseName"), result.getString("ExerciseType"));
        Call<Exercise> call1 = apiService.addExercise(exercise);
        call1.enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                getAllExercises();
            }

            @Override
            public void onFailure(Call<Exercise> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void initializeRecyclerView(@NonNull View view) {
        RecyclerView rvExercises = (RecyclerView) view.findViewById(R.id.rvExercises);

        exercisesAdapter = new ExercisesAdapter(exercises);
        rvExercises.setAdapter(exercisesAdapter);
        rvExercises.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getAllExercises() {
        exercises = new ArrayList<>();

        apiService = Network.getInstance().create(ApiService.class);

        Call<ArrayList<Exercise>> call1 = apiService.getExercises();
        call1.enqueue(new Callback<ArrayList<Exercise>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Exercise>> call, @NonNull Response<ArrayList<Exercise>> response) {
                assert response.body() != null;
                exercises.addAll(response.body());
                initializeRecyclerView(view);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Exercise>> call, @NonNull Throwable t) {
                call.cancel();
            }
        });
    }
}
