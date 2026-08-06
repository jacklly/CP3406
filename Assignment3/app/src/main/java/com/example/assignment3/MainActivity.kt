package com.example.assignment3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignment3.navigation.AppNav
import com.example.assignment3.navigation.AppNavContent
import com.example.assignment3.navigation.Screens
import com.example.assignment3.ui.theme.Assignment3Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment3Theme {

                val navController = rememberNavController()

                Scaffold {
                    AppNavContent(
                        modifier = Modifier.fillMaxSize(),
                        navHostController = navController,
                        onHomeClicked = { navController.navigate(Screens.HomeScreen.route) {
                            launchSingleTop = true
                        } },
                        onLearnClicked = { navController.navigate(Screens.LearnScreen.route) {
                            launchSingleTop = true
                        } },
                        onUserClicked = { navController.navigate(Screens.UserScreen.route) {
                            launchSingleTop = true
                        } },
                        onBackClicked = { navController.popBackStack() },
                        onSettingsClicked = { navController.navigate(Screens.SettingsScreen.route) {
                            launchSingleTop = true
                        } }
                    )
                }
            }
        }
    }
}
