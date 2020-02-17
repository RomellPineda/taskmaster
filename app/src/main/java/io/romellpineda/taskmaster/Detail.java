package io.romellpineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


//        TextView taskTitle = findViewById(R.id.textView3);
//        SharedPreferences sP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String selectedTask = sP.getString("task", "default task: play video games");
//        taskTitle.setText(selectedTask);
    }
}
