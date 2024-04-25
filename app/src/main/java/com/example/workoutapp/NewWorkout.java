package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;

import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewWorkout extends AppCompatActivity {

    public Button back, save;

    private EditText workoutTitle, workoutContent;

    private DatabaseReference databaseReference;

    static String title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_workout);

        back = findViewById(R.id.back);
        save = findViewById(R.id.save);
        workoutTitle = findViewById(R.id.title);
        workoutContent = findViewById(R.id.content);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        if(!getIntent().getStringExtra("TITLE").equals("blankTitle")){
            workoutTitle.setText(getIntent().getStringExtra("TITLE"));
            workoutContent.setText(getIntent().getStringExtra("CONTENT"));
        }

        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){


                title = workoutTitle.getText().toString().trim();
                content = workoutContent.getText().toString().trim();
                Workout workout = new Workout(title, content);

                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(NewWorkout.this, "Please add a title", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NewWorkout.this, "Please add content", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseReference.child("UserWorkouts").child(title).setValue(workout)
                        .addOnSuccessListener(aVoid ->
                                Toast.makeText(NewWorkout.this, "Journal Created", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e ->
                                Toast.makeText(NewWorkout.this, "Failed to create journal" + e.getMessage(), Toast.LENGTH_SHORT).show());

                Intent intent = new Intent(NewWorkout.this, ViewUserWorkouts.class);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(NewWorkout.this, ViewUserWorkouts.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
