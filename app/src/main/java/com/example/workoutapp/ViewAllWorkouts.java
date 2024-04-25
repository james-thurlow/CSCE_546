package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAllWorkouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_workouts);

        Button back = findViewById(R.id.back);
        Button newWorkout = findViewById(R.id.newWorkout);

        newWorkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ViewAllWorkouts.this, NewWorkout.class);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ViewAllWorkouts.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });






    }
}
