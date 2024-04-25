package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class NewWorkout extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_workout);

        Button back = findViewById(R.id.back);
        Button save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(NewWorkout.this, ViewAllWorkouts.class);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(NewWorkout.this, ViewAllWorkouts.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
