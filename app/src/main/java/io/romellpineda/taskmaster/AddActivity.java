package io.romellpineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    TaskDatabase taskDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        taskDb = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "task").allowMainThreadQueries().build();

        Task run = new Task("run", "do some cardio", "incomplete");
        Task clean = new Task("clean", "as needed", "incomplete");
        Task sleep = new Task("sleep", "required", "incomplete");
        taskDb.taskDao().saveTask(run);
        taskDb.taskDao().saveTask(clean);
        taskDb.taskDao().saveTask(sleep);


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
