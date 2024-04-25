package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.NewWorkout

class ViewGenericWorkout : AppCompatActivity() {
    var back: Button? = null
    var use: Button? = null
    private var showTitle: TextView? = null
    private var showContent: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_generic_workout)
        Companion.title = intent.getStringExtra("TITLE")
        content = intent.getStringExtra("CONTENT")
        back = findViewById(R.id.back)
        use = findViewById(R.id.use)
        showTitle = findViewById(R.id.title)
        showContent = findViewById(R.id.content)
        showTitle?.setText(Companion.title)
        showContent?.setText(content)
        back?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ViewGenericWorkout, ViewGenericWorkouts::class.java)
            startActivity(intent)
        })
        use?.setOnClickListener(View.OnClickListener {
            val useWorkout = Intent(this@ViewGenericWorkout, NewWorkout::class.java)
            useWorkout.putExtra("TITLE", Companion.title)
            useWorkout.putExtra("CONTENT", content)
            startActivity(useWorkout)
        })
    }

    companion object {
        var title: String? = null
        var content: String? = null
    }
}
