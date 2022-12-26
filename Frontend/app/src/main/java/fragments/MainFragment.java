package fragments;

import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class MainFragment extends Fragment {

    Button routineSection;
    Button exerciseSection;
    Fragment routineFragment;
    Fragment exerciseFragment;
//    NavHostController navHostController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        NavHostFragment navHostFragment =
//                (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.fragment_container_view);
//        navHostController = (NavHostController) navHostFragment.getNavController();
//        navHostController.navigate(R.id.action_workout_routine);
        routineSection = view.findViewById(R.id.routine_section);
        routineSection.setOnClickListener(routineSectionListener);
        exerciseSection = view.findViewById(R.id.exercise_section);
        exerciseSection.setOnClickListener(exerciseSectionListener);
        routineFragment = new RoutineSectionFragment();
        exerciseFragment = new ExerciseSectionFragment();
    }

    private View.OnClickListener routineSectionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Navigation.findNavController(v).navigate(R.id.action_exercise_routine);
//            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//            if (routineFragment.isAdded())
//                fragmentTransaction.show(routineFragment);
//            else
//                fragmentTransaction.add(R.id.fragment_container_view, routineFragment, "workout_section");
//            if (exerciseFragment.isAdded())
//                fragmentTransaction.hide(exerciseFragment);
//            fragmentTransaction.commit();
        }
    };

    private View.OnClickListener exerciseSectionListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

//            Navigation.findNavController(v).navigate(R.id.action_routine_exercise);
//            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//            if (exerciseFragment.isAdded())
//                fragmentTransaction.show(exerciseFragment);
//            else
//                fragmentTransaction.add(R.id.fragment_container_view, exerciseFragment, "exercise_section");
//            if (routineFragment.isAdded())
//                fragmentTransaction.hide(routineFragment);
//            fragmentTransaction.commit();
        }
    };
}
