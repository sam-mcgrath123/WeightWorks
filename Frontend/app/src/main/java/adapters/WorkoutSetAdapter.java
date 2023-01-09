package adapters;

import android.content.Context;
import android.example.weightworks.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import objects.Set;

public class WorkoutSetAdapter extends RecyclerView.Adapter<WorkoutSetAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView setId;
        public TextView previousSet;
        public EditText weight;
        public EditText reps;

        public ViewHolder(View itemView) {
            super(itemView);

            setId = itemView.findViewById(R.id.workout_set_id_text);
            previousSet = itemView.findViewById(R.id.workout_exercise_previous_text);
            weight = itemView.findViewById(R.id.workout_exercise_weight_text);
            reps = itemView.findViewById(R.id.workout_exercise_reps_text);
        }
    }

    private ArrayList<Set> sets;

    public WorkoutSetAdapter(ArrayList<Set> sets) {
        this.sets = sets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View exerciseView = inflater.inflate(R.layout.item_workout_set, parent, false);

        WorkoutSetAdapter.ViewHolder viewHolder = new ViewHolder(exerciseView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Set set = sets.get(position);

        TextView setIdTextView = holder.setId;
        setIdTextView.setText(Integer.toString(set.getSetId()));
        TextView previousTextView = holder.previousSet;
        previousTextView.setText(set.getPrevious());
        EditText weightTextView = holder.weight;
        weightTextView.setHint("Weight");
        EditText repsTextView = holder.reps;
        repsTextView.setHint("Reps");
    }

    @Override
    public int getItemCount() {
        return sets.size();
    }
}
