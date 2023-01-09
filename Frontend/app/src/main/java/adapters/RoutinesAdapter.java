package adapters;

import android.content.Context;
import android.example.weightworks.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import objects.Routine;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView routineName;
        public TextClock routineDate;
        public TextView routineExercises;

        public ViewHolder(View itemView) {
            super(itemView);

            routineName = (TextView) itemView.findViewById(R.id.routine_name);
            routineExercises = (TextView) itemView.findViewById(R.id.routine_exercises);
        }
    }

    private ArrayList<Routine> routines;

    public RoutinesAdapter(ArrayList<Routine> routines) {
        this.routines = routines;
    }

    @Override
    public RoutinesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View routineView = inflater.inflate(R.layout.item_routine, parent, false);

        ViewHolder viewHolder = new ViewHolder(routineView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RoutinesAdapter.ViewHolder holder, int position) {
        Routine routine = routines.get(position);

        TextView nameTextView = holder.routineName;
        nameTextView.setText(routine.getName());
        TextView exercisesTextView = holder.routineExercises;
        exercisesTextView.setText(routine.getExercises().toString().replace("[", "").replace("]", ""));
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }
}
