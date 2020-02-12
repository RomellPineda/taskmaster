package io.romellpineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTaskPage = findViewById(R.id.button);
        addTaskPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTaskPage = new Intent(MainActivity.this, AddActivity.class);
                MainActivity.this.startActivity(goToAddTaskPage);
            }
        });

        Button allTasksPage = findViewById(R.id.button2);
        allTasksPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTasksPage = new Intent(MainActivity.this, AllTasks.class);
                MainActivity.this.startActivity(goToAllTasksPage);
            }
        });

    }
}
