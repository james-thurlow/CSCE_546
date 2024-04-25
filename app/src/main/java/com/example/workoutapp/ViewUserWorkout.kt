package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewUserWorkout : AppCompatActivity() {
    var back: Button? = null
    private var showTitle: TextView? = null
    private var showContent: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_user_workout)
        Companion.title = intent.getStringExtra("TITLE")
        content = intent.getStringExtra("CONTENT")
        back = findViewById(R.id.back)
        showTitle = findViewById(R.id.title)
        showContent = findViewById(R.id.content)
        showTitle?.setText(Companion.title)
        showContent?.setText(content)
        back?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ViewUserWorkout, ViewUserWorkouts::class.java)
            startActivity(intent)
        })
    }

    companion object {
        var title: String? = null
        var content: String? = null
    }
}
