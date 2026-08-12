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
import coil.compose.AsyncImage
import com.example.assignment3.uiBuildParts.MasteryList
import org.json.JSONObject


@SuppressLint("DiscouragedApi")
@Composable
fun HomeScreen(

) {
    //background set
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = com.example.assignment3.R.drawable.background1),
        contentDescription = "background for landing page",
        contentScale = ContentScale.Crop
    )

    //Code

    //prep variables
    var username by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = remember { DatabaseProvider.getDatabase(context) }
    val scope = rememberCoroutineScope()
    var puuidValue by remember { mutableStateOf("") }
    var summonerInfo by remember { mutableStateOf(JSONObject()) }
    var champName: String? by remember { mutableStateOf("") }
    var imageRes: Int by remember { mutableIntStateOf(0) }
    var summonerLevel by remember { mutableIntStateOf(0) }
    var summonerIcon by remember { mutableIntStateOf(0) }

    //grab user data
    LaunchedEffect(Unit) {
        scope.launch {

            val user = db.userDao().getUser()

            summonerIcon = user?.summonerIcon ?: 0
            summonerLevel = user?.summonerLevel ?: 0
            puuidValue = user?.puuid ?: ""
            username = user?.username ?: "No user found"
        }
    }

    //UI
    Column(modifier = Modifier.fillMaxSize()) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp).padding(top = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
        ) {
            Row() {
                //Display summoner icon
                if (puuidValue != "") {
                    Box(modifier = Modifier.padding(5.dp)) {
                        AsyncImage(
                            model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/profileicon/$summonerIcon.png",
                            contentDescription = "summoner icon No. $summonerIcon",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(15.dp))
                        )
                    }
                }

                Column(modifier = Modifier.height(110.dp),
                    verticalArrangement = Arrangement.Center)
                {
                    //in-game name
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

                    //summoner level
                    Text(
                        text = "Level $summonerLevel",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }

        Text(
            text = "Your Champion Mastery",
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.White

        )

        //List off champs most played -> descending
        Card(
            modifier = Modifier.padding(horizontal = 10.dp).padding(bottom = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
        ) {
            if (puuidValue != "") {
                MasteryList(puuidValue)
            }
            else {
                Text(text = "Please go to Settings (top right) and set a user!\nFeel free to try: 'Aniki' in both boxes (creator's user)")
            }
        }
    }
}