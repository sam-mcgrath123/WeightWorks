package adapters;

import android.content.Context;
import android.example.weightworks.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import objects.Exercise;

public class WorkoutExerciseAdapter extends RecyclerView.Adapter<WorkoutExerciseAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView exerciseName;
        public RecyclerView sets;

        public Button addExerciseButton, deleteWorkoutButton;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            if (viewType == 0) {
                addExerciseButton = itemView.findViewById(R.id.new_workout_add_exercise_button);
                deleteWorkoutButton = itemView.findViewById(R.id.new_workout_delete_exercise_button);
            } else {
                exerciseName = itemView.findViewById(R.id.workout_exercise_name_text);
                sets = itemView.findViewById(R.id.workout_exercise_rv_sets);
            }
        }
    }

    private ArrayList<Exercise> exercises;

    public WorkoutExerciseAdapter(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v;
        ViewHolder vh;

        if (viewType == 0) {
            v = inflater.inflate(R.layout.item_workout_buttons, parent, false);
            vh = new ViewHolder(v, viewType);
            return vh;
        }
        v = inflater.inflate(R.layout.item_workout_exercise, parent, false);
        vh = new ViewHolder(v, viewType);
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 1;
        if (position == exercises.size())
            viewType = 0;
        return viewType;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position < exercises.size()) {
            Exercise exercise = exercises.get(position);

            TextView nameTextView = holder.exerciseName;
            nameTextView.setText(exercise.getName());

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(holder.sets.getContext(), LinearLayoutManager.VERTICAL, false);

            WorkoutSetAdapter workoutSetAdapter = new WorkoutSetAdapter(exercise.getSets());
            holder.sets.setLayoutManager(layoutManager);
            holder.sets.setAdapter(workoutSetAdapter);
            holder.sets.setRecycledViewPool(viewPool);
        }
        else {
            holder.addExerciseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "addExercise", Toast.LENGTH_LONG).show();
                }
            });
            holder.deleteWorkoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "deleteWorkout", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return exercises.size()+1;
    }
}
