package activities;

import android.example.weightworks.R;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.textView);
        title.setText(R.string.nav_new_workout);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavHostController navHostController = (NavHostController) navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostController);
        navHostController.addOnDestinationChangedListener(navControllerListener);
    }

    final private NavController.OnDestinationChangedListener navControllerListener = new NavController.OnDestinationChangedListener() {
        @Override
        public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
            switch (navDestination.getId()) {
                case (R.id.profile): {
                    title.setText(R.string.nav_profile);
                    break;
                }
                case (R.id.workout): {
                    title.setText(R.string.nav_new_workout);
                    if(navController.getCurrentDestination().getId() == R.id.newWorkoutFragment) {
                        navController.navigate(R.id.workout_fragment_container);
                    }
                    break;
                }
                case (R.id.history): {
                    title.setText(R.string.nav_history);
                    break;
                }
                case (R.id.newWorkoutFragment): {
                    title.setText("Push Day");
                }
            }
        }
    };
}