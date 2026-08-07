package com.example.assignment3.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.assignment3.api.getPuuidCall

@Composable
fun HomeScreen(

) {
    val puuid = getPuuidCall(gameName = "Aniki", tagLine = "Aniki")

    Text(text = "$puuid")
}