package com.example.helloworld.visuals.home

import com.example.helloworld.domain.model.Place
import com.example.helloworld.domain.util.PlaceOrder

sealed class PlaceEvent {
    data class Order(val placeOrder: PlaceOrder): PlaceEvent()
    data class DeletePlace(val place: Place): PlaceEvent()
    object RestorePlace: PlaceEvent()
    object ToggleOrderSection: PlaceEvent()
}
