package com.example.workoutapp

class Workout {
    @JvmField
    var title: String? = null
    @JvmField
    var content: String? = null

    constructor()
    constructor(title: String?, content: String?) {
        this.title = title
        this.content = content
    }
}
