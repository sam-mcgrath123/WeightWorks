package fragments;

import android.example.weightworks.R;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

public class WorkoutFragment extends Fragment {

    Button newWorkoutButton;
    Button routineSection;
    Button exerciseSection;
    Fragment newWorkoutFragment;
    Fragment routineFragment;
    Fragment exerciseFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout, parent, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        for (Fragment fragment : getChildFragmentManager().getFragments()) {
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
        }
        newWorkoutButton = view.findViewById(R.id.workout_new_workout_button);
        newWorkoutButton.setOnClickListener(newWorkoutListener);
        newWorkoutFragment = new NewWorkoutFragment();
        routineSection = view.findViewById(R.id.routine_section);
        routineSection.setOnClickListener(routineSectionListener);
        exerciseSection = view.findViewById(R.id.exercise_section);
        exerciseSection.setOnClickListener(exerciseSectionListener);
        routineFragment = new RoutineSectionFragment();
        exerciseFragment = new ExerciseSectionFragment();
        routineSectionListener.onClick(view);
        routineSection.setBackgroundColor(requireContext().getColor(R.color.purple_200));
    }

    private View.OnClickListener newWorkoutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.action_workout_newWorkout);
        }
    };

    final private View.OnClickListener routineSectionListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            routineSection.setBackgroundColor(requireContext().getColor(R.color.purple_200));
            exerciseSection.setBackgroundColor(requireContext().getColor(R.color.purple_500));
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            if (routineFragment.isAdded())
                fragmentTransaction.show(routineFragment);
            else
                fragmentTransaction.add(R.id.workout_fragment_container, routineFragment, "workout_section");
            if (exerciseFragment.isAdded())
                fragmentTransaction.hide(exerciseFragment);
            fragmentTransaction.commit();
        }
    };

    final private View.OnClickListener exerciseSectionListener = new View.OnClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            routineSection.setBackgroundColor(requireContext().getColor(R.color.purple_500));
            exerciseSection.setBackgroundColor(requireContext().getColor(R.color.purple_200));
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            if (exerciseFragment.isAdded())
                fragmentTransaction.show(exerciseFragment);
            else
                fragmentTransaction.add(R.id.workout_fragment_container, exerciseFragment, "exercise_section");
            if (routineFragment.isAdded())
                fragmentTransaction.hide(routineFragment);
            fragmentTransaction.commit();
        }
    };
}
