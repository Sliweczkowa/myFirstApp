package com.example.helloworld.visuals.home

import com.example.helloworld.domain.model.Place
import com.example.helloworld.domain.util.OrderType
import com.example.helloworld.domain.util.PlaceOrder

data class PlaceState(
    val places: List<Place> = emptyList(),
    val placeOrder: PlaceOrder = PlaceOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
