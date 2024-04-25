package com.example.workoutapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.NewWorkout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewGyms : AppCompatActivity(), RecyclerViewInterface {
    var back: Button? = null
    var newWorkout: Button? = null
    private var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_gyms)
        back = findViewById(R.id.back)
        newWorkout = findViewById(R.id.newWorkout)
        val recycler = findViewById<RecyclerView>(R.id.workoutRecycler)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.setLayoutManager(linearLayoutManager)
        workoutAdapter = WorkoutAdapter(this, workouts, this)
        recycler.setAdapter(workoutAdapter)
        databaseReference = FirebaseDatabase.getInstance().getReference()
        databaseReference!!.child("GymInfo")
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
        newWorkout?.setOnClickListener(View.OnClickListener {
            val blankTitle = "blankTitle"
            val blankContent = "blankContent"
            val intent = Intent(this@ViewGyms, NewWorkout::class.java)
            intent.putExtra("TITLE", blankTitle)
            intent.putExtra("CONTENT", blankContent)
            startActivity(intent)
            finish()
        })
        back?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ViewGyms, HomeScreen::class.java)
            startActivity(intent)
            finish()
        })
    }

    override fun onItemClick(position: Int) {
        //this code is activated when a workout is selected from the list
        val selectedWorkout = workouts[position]
        val phoneNumber = selectedWorkout.content

        // Create intent to initiate phone call
        val intent = Intent(Intent.ACTION_DIAL)
        intent.setData(Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }

    companion object {
        var workoutAdapter: WorkoutAdapter? = null
        var workouts = ArrayList<Workout>()
    }
}
