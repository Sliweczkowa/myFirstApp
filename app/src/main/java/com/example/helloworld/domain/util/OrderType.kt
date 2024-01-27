package com.example.helloworld.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}