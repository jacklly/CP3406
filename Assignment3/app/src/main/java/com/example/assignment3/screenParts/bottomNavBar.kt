package com.example.assignment3.screenParts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import com.example.assignment3.R
import com.example.assignment3.navigation.Screens

@Composable
fun BottomNavBar(
    onHomeClicked: () -> Unit,
    onLearnClicked: () -> Unit,
    onUserClicked: () -> Unit,
    currentScreenSet: String?,
    modifier: Modifier = Modifier
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        //Learn Screen
        NavigationBarItem(
            selected = currentScreenSet == Screens.LearnScreen.route,
            onClick = {onLearnClicked()},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_book_ribbon_24),
                    contentDescription = "Learn Icon",
                )
            }
        )

        //Home Screen
        NavigationBarItem(
            selected = currentScreenSet == Screens.HomeScreen.route,
            onClick = {onHomeClicked()},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_home_24),
                    contentDescription = "Learn Icon",
                )
            }
        )

        //User Screen
        NavigationBarItem(
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