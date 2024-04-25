package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        Button viewWorkout = findViewById(R.id.addWorkout);
        Button todayWorkout = findViewById(R.id.todayWorkout);
        Button hardware = findViewById(R.id.hardware);
        //Button back = findViewById(R.id.back);
        Button newWorkout = findViewById(R.id.newWorkout);

        newWorkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String blankTitle = "blankTitle";
                String blankContent = "blankContent";
                Intent intent = new Intent(HomeScreen.this, NewWorkout.class);
                intent.putExtra("TITLE", blankTitle);
                intent.putExtra("CONTENT", blankContent);
                startActivity(intent);
                finish();
            }
        });
        todayWorkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeScreen.this, ViewGenericWorkouts.class);
                startActivity(intent);
                finish();
            }
        });
        viewWorkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeScreen.this, ViewUserWorkouts.class);
                startActivity(intent);
                finish();
            }
        });

    }

}

