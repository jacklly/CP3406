package com.example.assignment3.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.assignment3.persistence.DatabaseProvider
import com.example.assignment3.uiBuildParts.MatchList
import kotlinx.coroutines.launch

@Composable
fun UserScreen(

) {
    //background set
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = com.example.assignment3.R.drawable.background2),
        contentDescription = "background for landing page",
        contentScale = ContentScale.Crop
    )

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { DatabaseProvider.getDatabase(context) }
    var puuid by remember { mutableStateOf("") }

    //grab puuid
    LaunchedEffect(Unit) {
        scope.launch {
            val user = db.userDao().getUser()
            puuid = user?.puuid ?: ""
        }
    }
    if (puuid != "") {
        MatchList(puuid)
    }
}