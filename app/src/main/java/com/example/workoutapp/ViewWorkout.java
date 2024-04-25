package com.example.workoutapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class ViewWorkout extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_workout);

        Button back = findViewById(R.id.back);
        Button use = findViewById(R.id.use);


        use.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ViewWorkout.this, NewWorkout.class);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ViewWorkout.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
