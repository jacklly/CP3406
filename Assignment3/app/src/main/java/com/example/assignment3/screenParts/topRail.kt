package com.example.assignment3.screenParts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.assignment3.R
import com.example.assignment3.navigation.Screens

@Composable
fun TopRail(
    onSettingsClicked: () -> Unit,
    onBackClicked: () -> Unit,
    currentScreenSet: String?,
    modifier: Modifier = Modifier
) {
    NavigationBar (modifier = Modifier.padding(top = 50.dp)) {

        //Back
        NavigationBarItem(
            selected = false,
            onClick = {onBackClicked()},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_arrow_back_24),
                    contentDescription = "Learn Icon",
                )
            }
        )

        //Settings Screen
        NavigationBarItem(
            selected = currentScreenSet == Screens.SettingsScreen.route,
            onClick = {onSettingsClicked()},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "Learn Icon",
                )
            }
        )
    }
}