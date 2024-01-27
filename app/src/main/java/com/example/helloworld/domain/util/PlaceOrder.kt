package com.example.helloworld.domain.util

sealed class PlaceOrder(val orderType: OrderType) {
    class PlaceName(orderType: OrderType): PlaceOrder(orderType)
    class Date(orderType: OrderType): PlaceOrder(orderType)
    class Rating(orderType: OrderType): PlaceOrder(orderType)
}
