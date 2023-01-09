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

import com.google.android.material.tabs.TabLayout;

public class WorkoutFragment extends Fragment {

    Button blankWorkout;
    Button routineSection;
    Button exerciseSection;
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
        blankWorkout = view.findViewById(R.id.start_empty_workout);
        blankWorkout.setOnClickListener(blankWorkoutListener);
        TabLayout tabLayout = view.findViewById(R.id.workout_tab_layout);
        tabLayout.addOnTabSelectedListener(tabSelectedListener);
        routineFragment = new RoutineSectionFragment();
        exerciseFragment = new ExerciseSectionFragment();
        TabLayout.Tab routineTab = tabLayout.getTabAt(0);
        tabSelectedListener.onTabSelected(routineTab);
    }

    final private View.OnClickListener blankWorkoutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.action_workout_newWorkout);
        }
    };

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case (0):
                    displaySection(routineFragment, exerciseFragment);
                    break;
                case (1):
                    displaySection(exerciseFragment, routineFragment);
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };

    private void displaySection(Fragment show, Fragment hide) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (show.isAdded())
            fragmentTransaction.show(show);
        else
            fragmentTransaction.add(R.id.workout_fragment_container, show, show.getTag());
        if (hide.isAdded())
            fragmentTransaction.hide(hide);
        fragmentTransaction.commit();
    }
}
