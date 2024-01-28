package com.example.helloworld.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "place")
data class Place (

    @PrimaryKey(autoGenerate = true)
    var placeId: Int,

    @ColumnInfo(name = "name")
    var placeName: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "rating")
    var rating: Int,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "photo")
    var photoPath: String = ""
)

class InvalidPlaceException(message: String): Exception(message)
