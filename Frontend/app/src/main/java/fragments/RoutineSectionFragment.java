package fragments;

import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;

import adapters.RoutinesAdapter;
import objects.Routine;

public class RoutineSectionFragment extends Fragment {

    ArrayList<Routine> routines;
    ArrayList<String> exercises;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_routine_section, parent, false);
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
        Routine routine1 = new Routine("Push Day", "1/1/22",
                new ArrayList<>(Arrays.asList("Bench Press", "Chest Press", "Tricep Pushdown")));
        Routine routine2 = new Routine("Pull Day", "2/2/22",
                new ArrayList<>(Arrays.asList("Lat Pulldown", "ISO-Lateral Row", "Incline Bicep Curl", "Preacher Curl")));
        Routine routine3 = new Routine("Leg Day", "3/3/22",
                new ArrayList<>(Arrays.asList("Squat", "Leg Press")));
        routines.add(routine1);
        routines.add(routine2);
        routines.add(routine3);
        routines.add(routine1);
        routines.add(routine2);
        routines.add(routine3);
        routines.add(routine1);
        routines.add(routine2);
        routines.add(routine3);
    }
}
