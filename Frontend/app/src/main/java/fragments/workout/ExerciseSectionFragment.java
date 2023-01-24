package fragments.workout;

import android.example.weightworks.R;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_section, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.view = view;
        getAllExercises();
    }

    private void initializeRecyclerView(@NonNull View view) {
        RecyclerView rvExercises = (RecyclerView) view.findViewById(R.id.rvExercises);

        ExercisesAdapter exercisesAdapter = new ExercisesAdapter(exercises);
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
