package com.example.assignment3.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment3.persistence.DatabaseProvider
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(

) {
    //Code
    var username by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = remember { DatabaseProvider.getDatabase(context) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {

            val user = db.userDao().getUser()

            username = user?.username ?: "No user found"

        }
    }

    //UI
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "$username",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
    }
}