package com.example.assignment3.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentScreenSet = navBackStackEntry?.destination?.route


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        
        TopRail(
            modifier = Modifier.statusBarsPadding(),
            onBackClicked = onBackClicked,
            onSettingsClicked = onSettingsClicked,
            currentScreenSet = currentScreenSet,
        )
        Box (Modifier.weight(1f)) {
            AppNav(navHostController = navHostController)
        }

        BottomNavBar(
            onHomeClicked = onHomeClicked,
            onLearnClicked = onLearnClicked,
            onUserClicked = onUserClicked,
            currentScreenSet = currentScreenSet,
        )
    }
}