package fragments;

import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainFragment extends Fragment {

    Button workoutSection;
    Button exerciseSection;
    Fragment workoutFragment;
    Fragment exerciseFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        workoutSection = view.findViewById(R.id.workout_section);
        workoutSection.setOnClickListener(workoutSectionListener);
        exerciseSection = view.findViewById(R.id.exercise_section);
        exerciseSection.setOnClickListener(exerciseSectionListener);
        workoutFragment = new RoutineSectionFragment();
        exerciseFragment = new ExerciseSectionFragment();
    }

    private View.OnClickListener workoutSectionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            if (workoutFragment.isAdded())
                fragmentTransaction.show(workoutFragment);
            else
                fragmentTransaction.add(R.id.fragment_container_view, workoutFragment, "workout_section");
            if (exerciseFragment.isAdded())
                fragmentTransaction.hide(exerciseFragment);
            fragmentTransaction.commit();
        }
    };

    private View.OnClickListener exerciseSectionListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            if (exerciseFragment.isAdded())
                fragmentTransaction.show(exerciseFragment);
            else
                fragmentTransaction.add(R.id.fragment_container_view, exerciseFragment, "exercise_section");
            if (workoutFragment.isAdded())
                fragmentTransaction.hide(workoutFragment);
            fragmentTransaction.commit();
        }
    };
}
