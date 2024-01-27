package com.example.helloworld.data

import com.example.helloworld.domain.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun getPlaces(): Flow<List<Place>>

    suspend fun getPlaceById(id: Int): Place?

    suspend fun insertPlace(place: Place)

    suspend fun deletePlace(place: Place)
}
