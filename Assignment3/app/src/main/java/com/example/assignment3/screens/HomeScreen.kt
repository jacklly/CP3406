package com.example.assignment3.screens

import android.R
import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment3.api.getMasteryList
import com.example.assignment3.dragontailAssets.championMap
import com.example.assignment3.persistence.DatabaseProvider
import kotlinx.coroutines.launch
import java.io.File
import androidx.compose.ui.platform.LocalResources
import com.example.assignment3.uiBuildParts.MasteryList


@SuppressLint("DiscouragedApi")
@Composable
fun HomeScreen(

) {
    //Code
    var username by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = remember { DatabaseProvider.getDatabase(context) }
    val scope = rememberCoroutineScope()
    var puuidValue by remember { mutableStateOf("") }
    var champ1 by remember { mutableStateOf(0) }
    var champName: String? by remember { mutableStateOf("") }
    var imageRes: Int by remember { mutableIntStateOf(0) }

    //grab user data
    LaunchedEffect(Unit) {
        scope.launch {

            val user = db.userDao().getUser()

            puuidValue = user?.puuid ?: "None"

            username = user?.username ?: "No user found"
        }
    }

    if (puuidValue != "") {
        //fetch champ with highest mastery
        val masteryList = getMasteryList(puuidValue)
        champ1 = masteryList?.getJSONObject(0)?.getInt("championId") ?: 0
        champName = championMap[champ1]

        Log.d("PUUIDVALUE", "[$puuidValue]")

        imageRes = LocalResources.current.getIdentifier(
            champName?.lowercase() ?: "",
            "drawable", context.packageName
        )
    }

    //UI
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = username,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp)

        Card(modifier = Modifier.fillMaxSize()) {
            Text(text = "$champName")
            if (imageRes != 0) {
                Card {
                    Image(
                        painter = painterResource(imageRes),
                        contentDescription = champName
                    )
                }
            }
            if (puuidValue != "") {
                MasteryList(puuidValue)
            }
        }
    }
}