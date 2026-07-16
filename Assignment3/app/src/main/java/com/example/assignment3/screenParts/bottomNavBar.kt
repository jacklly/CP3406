package com.example.assignment3.screenParts

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.assignment3.R
import com.example.assignment3.navigation.Screens

@Composable
fun BottomNavBar(
    onHomeClicked: () -> Unit,
    onLearnClicked: () -> Unit,
    onUserClicked: () -> Unit,
    currentScreenSet: () -> Unit,
) {

    NavigationRail() {
        NavigationRailItem(
            selected = currentScreenSet == Screens.LearnScreen.route,
            onClick = {onLearnClicked()},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_book_ribbon_24),
                    contentDescription = "Learn Icon",
                )
            }
        )
    }

    NavigationRail() {
        NavigationRailItem(
            selected = currentScreenSet == Screens.HomeScreen.route,
            onClick = {onHomeClicked()},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_home_24),
                    contentDescription = "Learn Icon",
                )
            }
        )
    }

    NavigationRail() {
        NavigationRailItem(
            selected = currentScreenSet == Screens.UserScreen.route,
            onClick = {onUserClicked()},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_frame_person_24),
                    contentDescription = "Learn Icon",
                )
            }
        )
    }
}