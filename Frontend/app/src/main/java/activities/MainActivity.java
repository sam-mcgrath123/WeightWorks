package activities;

import android.example.weightworks.R;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Remote.ApiService;
import Remote.Network;
import objects.Exercise;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    ApiService apiService;

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

        postExercise("Squat", "Barbell");
    }

    private void postExercise(String name, String type) {
        apiService = Network.getInstance().create(ApiService.class);

        Exercise exercise = new Exercise(name, type);
        Call<Exercise> call1 = apiService.addExercise(exercise);
        call1.enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                Exercise exercise1 = response.body();

                Toast.makeText(getApplicationContext(), exercise1.getName() + " " + exercise1.getId() + " " + exercise1.getType(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Exercise> call, Throwable t) {
                call.cancel();
            }
        });
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
                    break;
                }
                case (R.id.history): {
                    title.setText(R.string.nav_history);
                    break;
                }
            }
        }
    };
}