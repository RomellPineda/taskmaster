package io.romellpineda.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.ListTasksQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

public class MainActivity extends AppCompatActivity {

    private AWSAppSyncClient mainAwsAppSyncClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainAwsAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

        dynamoTasksQuery();


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

    public void dynamoTasksQuery(){
        mainAwsAppSyncClient.query(ListTasksQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(allTasksQueryCallback);
    }

    private GraphQLCall.Callback<ListTasksQuery.Data> allTasksQueryCallback = new GraphQLCall.Callback<ListTasksQuery.Data>() {
        @Override
        public void onResponse(@Nonnull Response<ListTasksQuery.Data> response) {
            Log.i("Results", response.data().listTasks().items().toString());
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("ERROR", e.toString());
        }
    };
}
