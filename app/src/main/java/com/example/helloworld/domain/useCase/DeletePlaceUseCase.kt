package com.example.helloworld.domain.useCase

import com.example.helloworld.data.PlaceRepository
import com.example.helloworld.domain.model.Place

class DeletePlaceUseCase(
    private val repository: PlaceRepository
) {
    suspend operator fun invoke(place: Place) {
        repository.deletePlace(place)
    }
}
