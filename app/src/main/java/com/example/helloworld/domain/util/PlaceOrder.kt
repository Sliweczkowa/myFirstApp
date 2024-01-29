package com.example.helloworld.domain.util

sealed class PlaceOrder(val orderType: OrderType) {
    class PlaceName(orderType: OrderType): PlaceOrder(orderType)
    class Date(orderType: OrderType): PlaceOrder(orderType)
    class Rating(orderType: OrderType): PlaceOrder(orderType)

    fun copy(orderType: OrderType): PlaceOrder {
        return when(this) {
            is PlaceName -> PlaceName(orderType)
            is Date -> Date(orderType)
            is Rating -> Rating(orderType)
        }
    }
}
