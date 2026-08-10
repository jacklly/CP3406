package com.example.assignment3.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment3.R
import com.example.assignment3.api.getPuuidCall
import com.example.assignment3.persistence.DatabaseProvider
import com.example.assignment3.persistence.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(

) {
    var puuid by remember { mutableStateOf("") }
    var textEntry by remember { mutableStateOf(emptyArray<String>()) }
    var gameName by remember { mutableStateOf("") }
    var tagLine by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = remember { DatabaseProvider.getDatabase(context) }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        OutlinedTextField(
            value = gameName,
            onValueChange = { gameName = it },
            label = { Text("Enter in game name:") },
            placeholder = { Text("Type something...") },
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).padding(10.dp)
        )

        OutlinedTextField(
            value = tagLine,
            onValueChange = { tagLine = it },
            label = { Text("# Enter tag line:") },
            placeholder = { Text("Type something...") },
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).padding(10.dp)
        )

        Button(onClick = {
            textEntry = arrayOf(gameName, tagLine)
        },
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_forward_24),
                contentDescription = "forward Icon",
                Modifier.size(20.dp)
            )
        }

        if (puuid != "") {
            Text(text = "User Saved",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp)
        }
    }

    if (textEntry.isNotEmpty()) {

        puuid = getPuuidCall(
            textEntry[0],
            textEntry[1]
        )

        LaunchedEffect(textEntry, puuid) {
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().saveUser(
                    User(
                        puuid = puuid,
                        username = "$gameName#$tagLine"
                    )
                )

            }
        }
    }
}