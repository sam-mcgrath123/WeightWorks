package activities;

import android.example.weightworks.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import fragments.ExerciseSectionFragment;
import fragments.WorkoutSectionFragment;

public class MainActivity extends AppCompatActivity {

    Button workoutSection;
    Button exerciseSection;
    Fragment workoutFragment;
    Fragment exerciseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView title = findViewById(R.id.textView);
        title.setText("New Workout");
        workoutSection = findViewById(R.id.workout_section);
        workoutSection.setOnClickListener(workoutSectionListener);
        exerciseSection = findViewById(R.id.exercise_section);
        exerciseSection.setOnClickListener(exerciseSectionListener);
        workoutFragment = new WorkoutSectionFragment();
        exerciseFragment = new ExerciseSectionFragment();
    }

    private View.OnClickListener workoutSectionListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment workoutSect = getSupportFragmentManager().findFragmentByTag("workout_section");
            Fragment exerciseSect = getSupportFragmentManager().findFragmentByTag("exercise_section");
            if (workoutSect != null)
                fragmentTransaction.show(workoutSect);
            else
                fragmentTransaction.add(R.id.fragment_container_view, new WorkoutSectionFragment(), "workout_section");
            if (exerciseSect != null)
                fragmentTransaction.hide(exerciseSect);
            fragmentTransaction.commit();
        }
    };

    private View.OnClickListener exerciseSectionListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment workoutSect = getSupportFragmentManager().findFragmentByTag("workout_section");
            Fragment exerciseSect = getSupportFragmentManager().findFragmentByTag("exercise_section");
            if (exerciseSect != null)
                fragmentTransaction.show(exerciseSect);
            else
                fragmentTransaction.add(R.id.fragment_container_view, new ExerciseSectionFragment(), "exercise_section");
            if (workoutSect != null)
                fragmentTransaction.hide(workoutSect);
            fragmentTransaction.commit();
        }
    };
}