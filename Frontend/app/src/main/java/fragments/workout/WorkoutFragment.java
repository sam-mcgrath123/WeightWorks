package fragments.workout;

import android.example.weightworks.R;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import Remote.ApiService;
import Remote.Network;
import objects.Exercise;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkoutFragment extends Fragment {

    private Button blankWorkout;
    private FloatingActionButton addItem;
    private Fragment routineFragment;
    private Fragment exerciseFragment;
    private Fragment draftFragment;
    ApiService apiService;

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
        draftFragment = new WorkoutDraftSectionFragment();
        TabLayout.Tab routineTab = tabLayout.getTabAt(0);
        tabSelectedListener.onTabSelected(routineTab);
        addItem = (FloatingActionButton) view.findViewById(R.id.workout_fab);
        addItem.setOnClickListener(fabListener);

        getChildFragmentManager().setFragmentResultListener("ExerciseAdded", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                postNewExercise(result);
            }
        });
    }

    private void postNewExercise(@NonNull Bundle result) {
        apiService = Network.getInstance().create(ApiService.class);

        Exercise exercise = new Exercise(result.getString("ExerciseName"), result.getString("ExerciseType"));
        Call<Exercise> call1 = apiService.addExercise(exercise);
        call1.enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
            }

            @Override
            public void onFailure(Call<Exercise> call, Throwable t) {
                call.cancel();
            }
        });
    }

    final private View.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showFabPopup(v);
        }
    };

    private void showFabPopup(View v) {
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        popupMenu.setOnMenuItemClickListener(fabMenuListener);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.workout_fab, popupMenu.getMenu());
        popupMenu.show();
    }

    final private PopupMenu.OnMenuItemClickListener fabMenuListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.fab_add_routine:
                    Log.d("Add", "Routine");
                    return true;
                case R.id.fab_add_exercise:
                    new AddExerciseFragment().show(getChildFragmentManager(), null);
                    return true;
                default:
                    return false;
            }
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
                    break;
                case (1):
                    displaySection(exerciseFragment, new Fragment[]{routineFragment, draftFragment});
                    break;
                case (2):
                    displaySection(draftFragment, new Fragment[]{routineFragment, exerciseFragment});
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
