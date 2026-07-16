package com.example.assignment3.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.assignment3.screenParts.TopRail
import com.example.assignment3.screenParts.BottomNavBar

@Composable
fun AppNavContent(
    navHostController: NavHostController,
    onHomeClicked: () -> Unit,
    onLearnClicked: () -> Unit = {},
    onUserClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    onSettingsClicked: () -> Unit,
    petsViewModel: PetsViewModel
) {
    val currentScreenSet = navBackStackEntry?.destination?.route

    Column() {
        TopRail(
            onBackClicked = onBackClicked,
            onSettingsClicked = onSettingsClicked
        )

        AppNav()

        BottomNavBar(
            onHomeClicked = onHomeClicked,
            onLearnClicked = onLearnClicked,
            onUserClicked = onUserClicked
        )
    }
}