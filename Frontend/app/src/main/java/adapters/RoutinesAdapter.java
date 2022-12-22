package adapters;

import android.content.Context;
import android.example.weightworks.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import objects.Routine;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView routineName;
        public TextClock routineDate;

        public ViewHolder(View itemView) {
            super(itemView);

            routineName = (TextView) itemView.findViewById(R.id.routine_name);
            routineDate = (TextClock) itemView.findViewById(R.id.routine_date);
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

        View routineView = inflater.inflate(R.layout.item_workout, parent, false);

        ViewHolder viewHolder = new ViewHolder(routineView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RoutinesAdapter.ViewHolder holder, int position) {
        Routine routine = routines.get(position);

        TextView nameTextView = holder.routineName;
        nameTextView.setText(routine.getName());
        TextClock dateTextClock = holder.routineDate;
        dateTextClock.setText(routine.getDate());
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }
}
