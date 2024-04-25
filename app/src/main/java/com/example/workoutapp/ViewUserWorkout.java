package com.example.workoutapp;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;

import android.widget.TextView;

public class ViewUserWorkout extends AppCompatActivity{

    public Button back;
    static String title, content;

    private TextView showTitle, showContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_user_workout);

        title = getIntent().getStringExtra("TITLE");
        content = getIntent().getStringExtra("CONTENT");

        back = findViewById(R.id.back)
;
        showTitle = findViewById(R.id.title);
        showContent = findViewById(R.id.content);

        showTitle.setText(title);
        showContent.setText(content);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewUserWorkout.this, ViewUserWorkouts.class);
                startActivity(intent);
            }
        });


    }


}
