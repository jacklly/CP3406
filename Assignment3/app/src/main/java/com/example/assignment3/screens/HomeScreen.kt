package com.example.assignment3.screens

import android.R
import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.service.autofill.Validators.and
import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
import com.example.assignment3.api.getSummonerLevel
import com.example.assignment3.uiBuildParts.MasteryList


@SuppressLint("DiscouragedApi")
@Composable
fun HomeScreen(

) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = com.example.assignment3.R.drawable.background1),
        contentDescription = "background for landing page",
        contentScale = ContentScale.Crop
    )

    //Code
    var username by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = remember { DatabaseProvider.getDatabase(context) }
    val scope = rememberCoroutineScope()
    var puuidValue by remember { mutableStateOf("") }
    var champ1 by remember { mutableStateOf(0) }
    var champ1Points by remember { mutableStateOf(0) }
    var champName: String? by remember { mutableStateOf("") }
    var imageRes: Int by remember { mutableIntStateOf(0) }

    //grab user data
    LaunchedEffect(Unit) {
        scope.launch {

            val user = db.userDao().getUser()

            puuidValue = user?.puuid ?: ""

            username = user?.username ?: "No user found"
        }
    }
    
    //ensure there's a puuid, then do:
    if (puuidValue != "") {
        val masteryList = getMasteryList(puuidValue)
        champ1 = masteryList?.getJSONObject(0)?.getInt("championId") ?: 0
        champ1Points = getSummonerLevel(puuidValue)
        champName = championMap[champ1]

        imageRes = LocalResources.current.getIdentifier(
            champName?.lowercase() ?: "",
            "drawable", context.packageName
        )
    }

    //UI
    Column(modifier = Modifier.fillMaxSize()) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
        ) {
            Row() {
                if (imageRes != 0) {
                    Box(modifier = Modifier.padding(5.dp)) {
                        Image(
                            painter = painterResource(imageRes),
                            contentDescription = champName,
                            Modifier.size(100.dp).clip(RoundedCornerShape(10.dp))
                        )
                    }
                }
                Column(modifier = Modifier.height(110.dp),
                    verticalArrangement = Arrangement.Center)
                {
                    Text(
                        text = username,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp,
                        color = Color.Black
                    )

                    Text(
                        text = "Level $champ1Points",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}