package com.example.helloworld.domain.util

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object ModifyScreen: Screen("modify_screen")
}