package com.example.helloworld.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.helloworld.domain.model.Place


@Database(
    entities = [Place::class],
    version = 1
)
abstract class PlaceDatabase: RoomDatabase() {

    abstract val placeDao: PlaceDao
    companion object {
        const val DATABASE_NAME = "place_db"
    }
}
