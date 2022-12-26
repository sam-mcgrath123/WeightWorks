package activities;

import android.example.weightworks.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fragments.ExerciseSectionFragment;
import fragments.RoutineSectionFragment;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView title = findViewById(R.id.textView);
        title.setText(R.string.title_new_workout);
//        TextView title = findViewById(R.id.textView);
//        title.setText(R.string.title_new_workout);
//        workoutSection = findViewById(R.id.workout_section);
//        workoutSection.setOnClickListener(workoutSectionListener);
//        exerciseSection = findViewById(R.id.exercise_section);
//        exerciseSection.setOnClickListener(exerciseSectionListener);
//        workoutFragment = new RoutineSectionFragment();
//        exerciseFragment = new ExerciseSectionFragment();

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavHostController navHostController = (NavHostController) navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostController);
    }
}