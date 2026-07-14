package com.example.assignment3.navigation

sealed class Screens(val route: String) {
    object HomeScreen: Screens("Home")
    object LearnScreen: Screens("Learn")
    object SettingsScreen: Screens("Settings")
    object UserScreen: Screens("User")
}