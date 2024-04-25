package com.example.workoutapp;

import java.util.ArrayList;

public class Workout {
    String title;
    String content;

    public Workout() {};

    public Workout(String title, String content){
            this.title = title;
            this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
