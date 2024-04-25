package com.example.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.widget.ImageButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class ViewGenericWorkouts extends AppCompatActivity implements RecyclerViewInterface{

    Button back;

    static WorkoutAdapter workoutAdapter;

    static ArrayList<Workout> workouts = new ArrayList<Workout>();

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_generic_workouts);

        back = findViewById(R.id.back);

        RecyclerView recycler = findViewById(R.id.workoutRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);

        workoutAdapter = new WorkoutAdapter(this, workouts, this);
        recycler.setAdapter(workoutAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("GenericWorkouts").addListenerForSingleValueEvent(new ValueEventListener() {

            //Updating the recycler view when new workouts are added.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                workouts.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String tempTitle = snapshot.child("title").getValue(String.class);
                    String tempContent = snapshot.child("content").getValue(String.class);

                    Workout workout = new Workout(tempTitle, tempContent);
                    workouts.add(workout);
                }



                if (ViewGenericWorkouts.workoutAdapter != null) {
                    ViewGenericWorkouts.workoutAdapter.notifyDataSetChanged();
                }
            }

            //Error message
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DatabaseError", error.getMessage());
            }
        });


        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ViewGenericWorkouts.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public void onItemClick(int position) {
        //this code is activated when a workout is selected from the list
        Intent view = new Intent(ViewGenericWorkouts.this, ViewGenericWorkout.class);

        view.putExtra("TITLE", workouts.get(position).getTitle());
        view.putExtra("CONTENT", workouts.get(position).getContent());

        startActivity(view);
    }
}