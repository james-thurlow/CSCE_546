package com.example.workoutapp;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
