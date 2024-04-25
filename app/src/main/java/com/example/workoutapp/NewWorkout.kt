package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewWorkout : AppCompatActivity() {
    var back: Button? = null
    var save: Button? = null
    private var workoutTitle: EditText? = null
    private var workoutContent: EditText? = null
    private var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_workout)
        back = findViewById(R.id.back)
        save = findViewById(R.id.save)
        workoutTitle = findViewById(R.id.title)
        workoutContent = findViewById(R.id.content)
        databaseReference = FirebaseDatabase.getInstance().getReference()
        if (intent.getStringExtra("TITLE") != "blankTitle") {
            workoutTitle?.setText(intent.getStringExtra("TITLE"))
            workoutContent?.setText(intent.getStringExtra("CONTENT"))
        }
        save?.setOnClickListener(View.OnClickListener {
            Companion.title = workoutTitle?.getText().toString().trim { it <= ' ' }
            content = workoutContent?.getText().toString().trim { it <= ' ' }
            val workout = Workout(Companion.title, content)
            if (TextUtils.isEmpty(Companion.title)) {
                Toast.makeText(this@NewWorkout, "Please add a title", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(content)) {
                Toast.makeText(this@NewWorkout, "Please add content", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            databaseReference!!.child("UserWorkouts").child(Companion.title!!).setValue(workout)
                .addOnSuccessListener { aVoid: Void? ->
                    Toast.makeText(
                        this@NewWorkout,
                        "Journal Created",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener { e: Exception ->
                    Toast.makeText(
                        this@NewWorkout,
                        "Failed to create journal" + e.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            val intent = Intent(this@NewWorkout, ViewUserWorkouts::class.java)
            startActivity(intent)
            finish()
        })
        back?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@NewWorkout, ViewUserWorkouts::class.java)
            startActivity(intent)
            finish()
        })
    }

    companion object {
        var title: String? = null
        var content: String? = null
    }
}
