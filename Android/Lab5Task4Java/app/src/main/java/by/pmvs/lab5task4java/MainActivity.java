package by.pmvs.lab5task4java;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btnSubscribe);
        button.setOnClickListener((view) -> {
            Snackbar.make(view, "The link is under the photo", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        });
    }
}