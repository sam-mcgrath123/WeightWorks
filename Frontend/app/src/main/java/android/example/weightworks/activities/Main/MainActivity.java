package android.example.weightworks.activities.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.example.weightworks.R;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView title = findViewById(R.id.textView);
        title.setText("New Workout");
    }
}