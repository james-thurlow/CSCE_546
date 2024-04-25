package com.example.workoutapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//Adapter class that works with WorkoutAdapter.ViewHolder to create a recycler view for the list of all workouts
class WorkoutAdapter //Constructor and data
    (
    private val context: Context,
    private val workouts: ArrayList<Workout>,
    private val recyclerViewInterface: RecyclerViewInterface
) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {
    private var databaseReference: DatabaseReference? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Creates view of each individual workout in the list
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workout_card, parent, false)
        return ViewHolder(view, recyclerViewInterface)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Assembles the array of workouts
        val workout = workouts[position]
        holder.title.text = workout.title
        holder.content.text = workout.content
        databaseReference = FirebaseDatabase.getInstance().getReference()
    }

    override fun getItemCount(): Int {
        return workouts.size
    } //Gets number of workouts

    class ViewHolder(itemView: View, recyclerViewInterface: RecyclerViewInterface?) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var content: TextView

        init {
            //Creates the recyclerview scrollable list of workouts.
            title = itemView.findViewById(R.id.workout_title)
            content = itemView.findViewById(R.id.workout_content)
            itemView.setOnClickListener {
                if (recyclerViewInterface != null) {
                    val pos = getAbsoluteAdapterPosition()
                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(pos)
                    }
                }
            }
        }
    }
}