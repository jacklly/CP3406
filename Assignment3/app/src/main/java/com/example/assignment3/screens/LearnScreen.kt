package com.example.assignment3.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun LearnScreen(

) {
    //background set
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = com.example.assignment3.R.drawable.background3),
        contentDescription = "background for landing page",
        contentScale = ContentScale.Crop
    )
    Column {
        Text(text = "Learn League!")


    }
}