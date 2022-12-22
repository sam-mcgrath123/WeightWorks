package fragments;

import android.example.weightworks.R;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import adapters.RoutinesAdapter;
import objects.Routine;

public class WorkoutSectionFragment extends Fragment {

    ArrayList<Routine> routines;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_workout_layout, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initializeRoutines();
        RecyclerView rvRoutines = (RecyclerView) view.findViewById(R.id.rvRoutines);

        RoutinesAdapter routinesAdapter = new RoutinesAdapter(routines);
        rvRoutines.setAdapter(routinesAdapter);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvRoutines.setLayoutManager(gridLayoutManager);
    }

    private void initializeRoutines() {
        routines = new ArrayList<>();
        Routine routine1 = new Routine("Push Day", "1/1/22");
        Routine routine2 = new Routine("Pull Day", "2/2/22");
        Routine routine3 = new Routine("Leg Day", "3/3/22");
        routines.add(routine1);
        routines.add(routine2);
        routines.add(routine3);
    }
}
