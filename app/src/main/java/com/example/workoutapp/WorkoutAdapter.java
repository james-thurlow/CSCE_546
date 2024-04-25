package com.example.workoutapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
//Adapter class that works with WorkoutAdapter.ViewHolder to create a recycler view for the list of all workouts
public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {
    private final Context context;
    private final RecyclerViewInterface recyclerViewInterface;
    private final ArrayList<Workout> workouts;
    private DatabaseReference databaseReference;

    //Constructor and data
    public WorkoutAdapter(Context context, ArrayList<Workout> workouts, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.workouts = workouts;
        this.recyclerViewInterface = recyclerViewInterface;
    }



    @NonNull
    @Override
    public WorkoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Creates view of each individual workout in the list
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_card, parent, false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Assembles the array of workouts
        Workout workout = workouts.get(position);
        holder.title.setText(workout.getTitle());
        holder.content.setText(workout.getContent());
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public int getItemCount() {
        return workouts.size();
    } //Gets number of workouts

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView content;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            //Creates the recyclerview scrollable list of workouts.
            super(itemView);
            title = itemView.findViewById(R.id.workout_title);
            content = itemView.findViewById(R.id.workout_content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getAbsoluteAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}