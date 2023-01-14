package fragments;

import android.example.weightworks.R;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;

public class WorkoutFragment extends Fragment {

    Fragment routineFragment;
    Fragment exerciseFragment;
    Fragment draftFragment;
    LinearLayoutCompat linearLayoutCompat;
    Button blankWorkout;
    AppCompatTextView newItem;

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
        newItem = view.findViewById(R.id.workout_new_item_text);
        newItem.setText(R.string.new_routine);
        blankWorkout = view.findViewById(R.id.start_empty_workout);
        blankWorkout.setOnClickListener(blankWorkoutListener);
        TabLayout tabLayout = view.findViewById(R.id.workout_tab_layout);
        tabLayout.addOnTabSelectedListener(tabSelectedListener);
        routineFragment = new RoutineSectionFragment();
        exerciseFragment = new ExerciseSectionFragment();
        draftFragment = new WorkoutDraftSectionFragment();
        TabLayout.Tab routineTab = tabLayout.getTabAt(0);
        tabSelectedListener.onTabSelected(routineTab);
        linearLayoutCompat = view.findViewById(R.id.workout_new_linear_layout);
        linearLayoutCompat.setOnClickListener(newItemListener);
    }

    final private View.OnClickListener newItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "Toast", Toast.LENGTH_SHORT).show();
        }
    };

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
                    displaySection(routineFragment, new Fragment[]{exerciseFragment, draftFragment});
                    if (linearLayoutCompat != null) {
                        linearLayoutCompat.setVisibility(View.VISIBLE);
                    }
                    newItem.setText(R.string.new_routine);
                    break;
                case (1):
                    displaySection(exerciseFragment, new Fragment[]{routineFragment, draftFragment});
                    linearLayoutCompat.setVisibility(View.VISIBLE);
                    newItem.setText(R.string.new_exercise);
                    break;
                case (2):
                    displaySection(draftFragment, new Fragment[]{routineFragment, exerciseFragment});
                    linearLayoutCompat.setVisibility(View.INVISIBLE);
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

    private void displaySection(Fragment show, Fragment[] fragmentsToHide) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (show.isAdded())
            fragmentTransaction.show(show);
        else
            fragmentTransaction.add(R.id.workout_fragment_container, show, show.getTag());
        for (Fragment hide : fragmentsToHide) {
            if (hide.isAdded())
                fragmentTransaction.hide(hide);
        }
        fragmentTransaction.commit();
    }
}
