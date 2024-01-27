package com.example.helloworld.domain.useCase

import com.example.helloworld.data.PlaceRepository
import com.example.helloworld.domain.model.Place
import com.example.helloworld.domain.util.OrderType
import com.example.helloworld.domain.util.PlaceOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPlacesUseCase(
    private val repository: PlaceRepository
) {
    operator fun invoke(
        placeOrder: PlaceOrder = PlaceOrder.Date(OrderType.Descending)
    ): Flow<List<Place>> {
        return repository.getPlaces().map { places ->
            when(placeOrder.orderType) {
                is OrderType.Ascending -> {
                    when(placeOrder) {
                        is PlaceOrder.PlaceName -> places.sortedBy { it.placeName.lowercase() }
                        is PlaceOrder.Date -> places.sortedBy { it.date }
                        is PlaceOrder.Rating -> places.sortedBy { it.rating }
                    }
                }
                is OrderType.Descending -> {
                    when(placeOrder) {
                        is PlaceOrder.PlaceName -> places.sortedByDescending { it.placeName.lowercase() }
                        is PlaceOrder.Date -> places.sortedByDescending { it.date }
                        is PlaceOrder.Rating -> places.sortedByDescending { it.rating }
                    }
                }
            }
        }
    }
}
