package io.romellpineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Task> tasksFromDatabase;
    TaskDatabase taskDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskDb = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "task").allowMainThreadQueries().build();
        this.tasksFromDatabase = new ArrayList<Task>();

        TextView appTitle = findViewById(R.id.textView);
        SharedPreferences sP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String user = sP.getString("username", "My Tasks");
        appTitle.setText(user);

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

        Button settingsPage =  findViewById(R.id.settingsButton);
        settingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSetting = new Intent(MainActivity.this, Setting.class);
                MainActivity.this.startActivity(goToSetting);
            }
        });

//        Archive pre-Recycler
//        final RadioGroup taskGroup = findViewById(R.id.taskGroup);
//        taskGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                String waffle = ((RadioButton)findViewById(taskGroup.getCheckedRadioButtonId())).getText().toString();
//
//                SharedPreferences sP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = sP.edit();
//                editor.putString("task", waffle);
//                editor.apply();
//
//                Intent goToDetailPage = new Intent(MainActivity.this, Detail.class);
//                MainActivity.this.startActivity(goToDetailPage);
//            }
//        });
    }
}
