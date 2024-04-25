package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.ViewGenericWorkout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewGenericWorkouts : AppCompatActivity(), RecyclerViewInterface {
    var back: Button? = null
    private var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_generic_workouts)
        back = findViewById(R.id.back)
        val recycler = findViewById<RecyclerView>(R.id.workoutRecycler)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.setLayoutManager(linearLayoutManager)
        workoutAdapter = WorkoutAdapter(this, workouts, this)
        recycler.setAdapter(workoutAdapter)
        databaseReference = FirebaseDatabase.getInstance().getReference()
        databaseReference!!.child("GenericWorkouts")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                //Updating the recycler view when new workouts are added.
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    workouts.clear()
                    for (snapshot in dataSnapshot.getChildren()) {
                        val tempTitle = snapshot.child("title").getValue(
                            String::class.java
                        )
                        val tempContent = snapshot.child("content").getValue(
                            String::class.java
                        )
                        val workout = Workout(tempTitle, tempContent)
                        workouts.add(workout)
                    }
                    if (workoutAdapter != null) {
                        workoutAdapter!!.notifyDataSetChanged()
                    }
                }

                //Error message
                override fun onCancelled(error: DatabaseError) {
                    Log.d("DatabaseError", error.message)
                }
            })
        back?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ViewGenericWorkouts, HomeScreen::class.java)
            startActivity(intent)
            finish()
        })
    }

    override fun onItemClick(position: Int) {
        //this code is activated when a workout is selected from the list
        val view = Intent(this@ViewGenericWorkouts, ViewGenericWorkout::class.java)
        view.putExtra("TITLE", workouts[position].title)
        view.putExtra("CONTENT", workouts[position].content)
        startActivity(view)
    }

    companion object {
        var workoutAdapter: WorkoutAdapter? = null
        var workouts = ArrayList<Workout>()
    }
}