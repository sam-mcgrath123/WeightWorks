package adapters;

import android.content.Context;
import android.example.weightworks.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import objects.Exercise;

public class HistoryExerciseAdapter extends RecyclerView.Adapter<HistoryExerciseAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView exerciseName;
        public TextView exerciseSet;

        public ViewHolder(View itemView) {
            super(itemView);

            exerciseName = (TextView) itemView.findViewById(R.id.history_exercise_name_text);
            exerciseSet = (TextView) itemView.findViewById(R.id.history_exercise_set_text);
        }

    }

    private ArrayList<Exercise> exercises;

    public HistoryExerciseAdapter(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View exerciseView = inflater.inflate(R.layout.item_history_exercise, parent, false);

        HistoryExerciseAdapter.ViewHolder viewHolder = new ViewHolder(exerciseView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);

        TextView nameTextView = holder.exerciseName;
        nameTextView.setText(exercise.getName());
        TextView setTextView = holder.exerciseSet;
        setTextView.setText(exercise.getSet());
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
