package com.example.helloworld.domain.useCase

import com.example.helloworld.data.PlaceRepository
import com.example.helloworld.domain.model.InvalidPlaceException
import com.example.helloworld.domain.model.Place
import kotlin.jvm.Throws

class InsertPlaceUseCase(
    private val repository: PlaceRepository
) {
    @Throws(InvalidPlaceException::class)
    suspend operator fun invoke(place: Place) {
        if(place.placeName.isBlank()) {
            throw InvalidPlaceException("Place name cannot be empty")
        }
//        TODO: Check provided date format
//        else if(!"""([0-2][0-9]|3[0-1])\.(0[0-9]|1[0-2])\.[1-2][0-9]{3}""".toRegex().matches(place.date)) {
//            throw InvalidPlaceException("Date should be provided in dd.mm.yyyy format")
//        }
//        TODO: Convert to same date format
//        else if(parse(place.date) > LocalDate.now()) {
//            throw InvalidPlaceException("Date cannot be in the future")
//        }
        else if(place.rating == 0) {
            throw InvalidPlaceException("Rating cannot be equal 0")
        }
        else if(place.description.isBlank()) {
            throw InvalidPlaceException("Description cannot be empty")
        }
        repository.insertPlace(place)
    }
}
