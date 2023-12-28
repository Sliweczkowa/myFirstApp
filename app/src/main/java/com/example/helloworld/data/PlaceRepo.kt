package com.example.helloworld.data

import com.example.helloworld.Place
import kotlinx.coroutines.flow.Flow


class PlaceRepo(
    private val dao: PlaceDao
) {

    fun getPlaces(): Flow<List<Place>> {
        return dao.getPlaces()
    }

    suspend fun getPlaceById(id: Int): Place? {
        return dao.getPlaceById(id)
    }

    suspend fun insertPlace(place: Place) {
        dao.insertPlace(place)
    }

    suspend fun deletePlace(place: Place) {
        dao.deletePlace(place)
    }
}
