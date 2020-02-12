package io.romellpineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button publishTask = findViewById(R.id.button3);
        publishTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast success = Toast.makeText(getApplicationContext(), "successfully published task", Toast.LENGTH_SHORT);
                success.show();
            }
        });
    }
}
