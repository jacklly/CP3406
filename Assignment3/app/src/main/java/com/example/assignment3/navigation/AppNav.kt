package com.example.assignment3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment3.screens.HomeScreen
import com.example.assignment3.screens.LearnScreen
import com.example.assignment3.screens.SettingsScreen
import com.example.assignment3.screens.UserScreen


@Composable
fun AppNav(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.HomeScreen.route
    ) {
        //Home Screen
        composable(Screens.HomeScreen.route) {
            HomeScreen()
        }

        //Learn Screen
        composable(Screens.LearnScreen.route) {
            LearnScreen()
        }

        //Settings Screen
        composable(Screens.SettingsScreen.route) {
            SettingsScreen()
        }

        //User Screen
        composable(Screens.UserScreen.route) {
            UserScreen()
        }
    }
}