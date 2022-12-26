package fragments;

import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainFragment extends Fragment {

    Button routineSection;
    Button exerciseSection;
    Fragment routineFragment;
    Fragment exerciseFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        routineSection = view.findViewById(R.id.routine_section);
        routineSection.setOnClickListener(routineSectionListener);
        exerciseSection = view.findViewById(R.id.exercise_section);
        exerciseSection.setOnClickListener(exerciseSectionListener);
        if(routineFragment == null) {
            routineFragment = new RoutineSectionFragment();
        }
        if(exerciseFragment == null) {
            exerciseFragment = new ExerciseSectionFragment();
        }
        routineSectionListener.onClick(view);
        routineSection.setBackgroundColor(getResources().getColor(R.color.purple_200));
    }

    final private View.OnClickListener routineSectionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            routineSection.setBackgroundColor(getResources().getColor(R.color.purple_200));
            exerciseSection.setBackgroundColor(getResources().getColor(R.color.purple_500));
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            if (routineFragment.isAdded())
                fragmentTransaction.show(routineFragment);
            else
                fragmentTransaction.add(R.id.fragment_container_view, routineFragment, "workout_section");
            if (exerciseFragment.isAdded())
                fragmentTransaction.hide(exerciseFragment);
            fragmentTransaction.commit();
        }
    };

    final private View.OnClickListener exerciseSectionListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            routineSection.setBackgroundColor(getResources().getColor(R.color.purple_500));
            exerciseSection.setBackgroundColor(getResources().getColor(R.color.purple_200));
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            if (exerciseFragment.isAdded())
                fragmentTransaction.show(exerciseFragment);
            else
                fragmentTransaction.add(R.id.fragment_container_view, exerciseFragment, "exercise_section");
            if (routineFragment.isAdded())
                fragmentTransaction.hide(routineFragment);
            fragmentTransaction.commit();
        }
    };
}
