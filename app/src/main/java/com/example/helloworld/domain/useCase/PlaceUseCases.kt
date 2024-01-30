package com.example.helloworld.domain.useCase

data class PlaceUseCases(
    val getPlaces: GetPlacesUseCase,
    val getPlaceById: GetPlaceByIDUseCase,
    val deletePlace: DeletePlaceUseCase,
    val insertPlace: InsertPlaceUseCase
)
