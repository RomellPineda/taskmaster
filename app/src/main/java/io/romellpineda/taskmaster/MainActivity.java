package io.romellpineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

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

        RadioGroup taskGroup = findViewById(R.id.taskGroup);
        taskGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

//            EditText nameInput = findViewById(R.id.username);
//            String username = nameInput.getText().toString();


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String waffle = "joe";

                Log.v("groupo", group.toString());

                SharedPreferences sP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sP.edit();
                editor.putString("username", waffle);

                Intent goToDetailPage = new Intent(MainActivity.this, Detail.class);
                MainActivity.this.startActivity(goToDetailPage);
            }
        });
    }
}
