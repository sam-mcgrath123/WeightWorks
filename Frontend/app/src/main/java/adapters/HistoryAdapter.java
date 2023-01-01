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

import objects.CompletedWorkout;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView date;
        public TextView duration;
        public TextView totalWeight;
        public TextView numOfPrs;
        public RecyclerView exercises;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.history_workout_name_text);
            date = (TextView) itemView.findViewById(R.id.history_workout_date_text);
            duration = (TextView) itemView.findViewById(R.id.history_min_text);
            totalWeight = (TextView) itemView.findViewById(R.id.history_lbs_text);
            numOfPrs = (TextView) itemView.findViewById(R.id.history_prs_text);
            exercises = (RecyclerView) itemView.findViewById(R.id.history_rv_exercises);
        }
    }

    private ArrayList<CompletedWorkout> completedWorkouts;

    public HistoryAdapter(ArrayList<CompletedWorkout> completedWorkouts) {
        this.completedWorkouts = completedWorkouts;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View historyView = inflater.inflate(R.layout.item_history, parent, false);

        HistoryAdapter.ViewHolder viewHolder = new ViewHolder(historyView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        CompletedWorkout completedWorkout = completedWorkouts.get(position);

        TextView tempTextView = holder.name;
        tempTextView.setText(completedWorkout.getName());
        tempTextView = holder.date;
        tempTextView.setText(completedWorkout.getDate());
        tempTextView = holder.duration;
        tempTextView.setText(completedWorkout.getDuration());
        tempTextView = holder.totalWeight;
        tempTextView.setText(completedWorkout.getTotalWeight());
        tempTextView = holder.numOfPrs;
        tempTextView.setText(completedWorkout.getNumOfPRs());

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(holder.exercises.getContext(), LinearLayoutManager.VERTICAL, false);


        HistoryExerciseAdapter historyExerciseAdapter = new HistoryExerciseAdapter(completedWorkout.getExercises());
        holder.exercises.setLayoutManager(layoutManager);
        holder.exercises.setAdapter(historyExerciseAdapter);
        holder.exercises.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return completedWorkouts.size();
    }
}
