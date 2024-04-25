package com.example.workoutapp;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

//firebase imports
import com.google.firebase.database.DataSnapshot;

public class Login {



    private class User{
        public String username;
        public String password;
        public ArrayList<Workout> workouts;

        public User() {}

        public User(String username, String password){
            this.username = username;
            this.password = password;
        }

        public User(String username, String password, ArrayList<Workout> workouts){
            this.username = username;
            this.password = password;
            this.workouts = workouts;

        }
    }
}
