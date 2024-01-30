package com.example.helloworld.domain.useCase

import com.example.helloworld.data.PlaceRepository
import com.example.helloworld.domain.model.Place

class GetPlaceByIDUseCase(
    private val repository: PlaceRepository
) {
    suspend operator fun invoke(id: Int): Place? {
        return repository.getPlaceById(id)
    }
}