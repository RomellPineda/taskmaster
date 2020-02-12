package io.romellpineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button sendUsername = findViewById(R.id.button4);
        sendUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameInput = findViewById(R.id.username);
                String username = nameInput.getText().toString();
                Toast usernameSubmitted = Toast.makeText(getApplicationContext(), username, Toast.LENGTH_SHORT);
                usernameSubmitted.show();
            }
        });
    }
}
