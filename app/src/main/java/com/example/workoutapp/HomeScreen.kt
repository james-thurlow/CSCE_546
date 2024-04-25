package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        val viewWorkout = findViewById<Button>(R.id.addWorkout)
        val todayWorkout = findViewById<Button>(R.id.todayWorkout)
        val hardware = findViewById<Button>(R.id.hardware)
        val newWorkout = findViewById<Button>(R.id.newWorkout)
        newWorkout.setOnClickListener {
            val blankTitle = "blankTitle"
            val blankContent = "blankContent"
            val intent = Intent(this@HomeScreen, NewWorkout::class.java)
            intent.putExtra("TITLE", blankTitle)
            intent.putExtra("CONTENT", blankContent)
            startActivity(intent)
            finish()
        }
        todayWorkout.setOnClickListener {
            val intent = Intent(this@HomeScreen, ViewGenericWorkouts::class.java)
            startActivity(intent)
            finish()
        }
        viewWorkout.setOnClickListener {
            val intent = Intent(this@HomeScreen, ViewUserWorkouts::class.java)
            startActivity(intent)
            finish()
        }

        hardware.setOnClickListener {
            val intent = Intent(this@HomeScreen, ViewGyms::class.java)
            startActivity(intent)
            finish()
        }

    }
}
