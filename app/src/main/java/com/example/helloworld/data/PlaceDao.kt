package com.example.helloworld.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.helloworld.Place
import kotlinx.coroutines.flow.Flow

interface PlaceDao {
    @Query("SELECT * FROM Place")
    fun getPlaces(): Flow<List<Place>>

    @Query("SELECT * FROM Place WHERE placeId = :id")
    suspend fun getPlaceById(id: Int): Place?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: Place)

    @Delete
    suspend fun deletePlace(place: Place)
}
