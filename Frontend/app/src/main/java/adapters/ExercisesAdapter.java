package adapters;

import android.content.Context;
import android.example.weightworks.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import objects.Exercise;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView exerciseName;
        public TextView exerciseType;
        public TextView exerciseSet;

        public ViewHolder(View itemView) {
            super(itemView);

            exerciseName = (TextView) itemView.findViewById(R.id.exercise_name);
            exerciseType = (TextView) itemView.findViewById(R.id.exercise_type);
            exerciseSet = (TextView) itemView.findViewById(R.id.exercise_set);
        }
    }

    private ArrayList<Exercise> exercises;

    public ExercisesAdapter(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public ExercisesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View exerciseView = inflater.inflate(R.layout.item_exercise, parent, false);

        ViewHolder viewHolder = new ViewHolder(exerciseView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExercisesAdapter.ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);

        TextView nameTextView = holder.exerciseName;
        nameTextView.setText(exercise.getName());
        TextView typeTextView = holder.exerciseType;
        typeTextView.setText(exercise.getType());
        TextView setTextView = holder.exerciseSet;
        setTextView.setText(exercise.getSet());
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
