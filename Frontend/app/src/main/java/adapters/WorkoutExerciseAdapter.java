package adapters;

import android.content.Context;
import android.example.weightworks.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import objects.Exercise;

public class WorkoutExerciseAdapter extends RecyclerView.Adapter<WorkoutExerciseAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView exerciseName;
        public RecyclerView sets;

        public ViewHolder(View itemView) {
            super(itemView);

            exerciseName = itemView.findViewById(R.id.workout_exercise_name_text);
            sets = itemView.findViewById(R.id.workout_exercise_rv_sets);
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

        View exerciseView = inflater.inflate(R.layout.item_workout_exercise, parent, false);

        WorkoutExerciseAdapter.ViewHolder viewHolder = new ViewHolder(exerciseView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
