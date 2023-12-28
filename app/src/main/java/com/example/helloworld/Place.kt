package com.example.helloworld

import androidx.room.Entity


@Entity
data class Place (
    var placeName: String,
    var date: String,
    var rating: Int,
    var description: String,
    var photoPath: String = ""
)
