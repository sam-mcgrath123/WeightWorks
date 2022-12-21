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
        title.setText(R.string.title_new_workout);
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
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
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