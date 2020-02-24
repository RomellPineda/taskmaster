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
import android.widget.Toast;

import com.amazonaws.amplify.generated.graphql.CreateTaskMutation;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

import type.CreateTaskInput;

public class Setting extends AppCompatActivity {

    private AWSAppSyncClient awsAppSyncClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        awsAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

        Button sendUsername = findViewById(R.id.button4);
        sendUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText body = findViewById(R.id.body);
                String taskBody = body.getText().toString();
                EditText title = findViewById(R.id.title);
                String taskTitle = title.getText().toString();
                Toast usernameSubmitted = Toast.makeText(getApplicationContext(), taskTitle, Toast.LENGTH_SHORT);
                usernameSubmitted.show();
                SharedPreferences sP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sP.edit();
                editor.putString("username", taskTitle);
                editor.apply();

                runTaskMutation(taskTitle, taskBody);

                Intent goBackToMain = new Intent(Setting.this, MainActivity.class);
                Setting.this.startActivity(goBackToMain);
            }
        });
    }

    public void runTaskMutation(String title, String body){
        CreateTaskInput createTaskInput = CreateTaskInput.builder()
                .title(title)
                .body(body)
                .state("pending")
                .build();

        awsAppSyncClient.mutate(CreateTaskMutation.builder().input(createTaskInput).build())
                .enqueue(addMutationCallback);
    }

    private GraphQLCall.Callback<CreateTaskMutation.Data> addMutationCallback = new GraphQLCall.Callback<CreateTaskMutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateTaskMutation.Data> response) {
            Log.i("Results", "Added code task");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }
    };
}
