package com.example.assignment3.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment3.R
import com.example.assignment3.api.getMasteryList
import com.example.assignment3.api.getPuuidCall
import com.example.assignment3.api.getRank
import com.example.assignment3.api.getSummonerInfo
import com.example.assignment3.dragontailAssets.championMap
import com.example.assignment3.persistence.DatabaseProvider
import com.example.assignment3.persistence.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

@Composable
fun SettingsScreen(

) {
    //background set
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background1),
        contentDescription = "background for landing page",
        contentScale = ContentScale.Crop
    )

    //prep variables
    var puuid by remember { mutableStateOf("") }
    var textEntry by remember { mutableStateOf(emptyArray<String>()) }
    var gameName by remember { mutableStateOf("") }
    var tagLine by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = remember { DatabaseProvider.getDatabase(context) }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp).padding(top = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
        ) {
            OutlinedTextField(
                value = gameName,
                onValueChange = { gameName = it },
                label = { Text("Enter in game name:") },
                placeholder = { Text("Type something...") },
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )

            OutlinedTextField(
                value = tagLine,
                onValueChange = { tagLine = it },
                label = { Text("# Enter tag line:") },
                placeholder = { Text("Type something...") },
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )

            Button(
                onClick = {
                    textEntry = arrayOf(gameName, tagLine)
                },
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_forward_24),
                    contentDescription = "forward Icon",
                    Modifier.size(20.dp)
                )
            }
        }

        if (puuid != "") {
            Text(text = "User Saved",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                color = Color.Magenta)
        }
    }

    //ensure the user has input something
    if (textEntry.isNotEmpty()) {

        //get users puuid (integral to api call(s))
        puuid = getPuuidCall(
            textEntry[0],
            textEntry[1]
        )

        //get jsonobject with ranked info
        var rankedInfo: JSONObject? = JSONObject()
        var summonerInfo: JSONObject? = JSONObject()

        //ensure there's a puuid
        if (puuid != "") {

            rankedInfo = getRank(puuid)
            summonerInfo = getSummonerInfo(puuid)

            //ensure we have rankedinfo
            if (rankedInfo != null) {
                //save user info to room
                LaunchedEffect(textEntry, puuid, rankedInfo) {
                    CoroutineScope(Dispatchers.IO).launch {
                        db.userDao().saveUser(
                            User(
                                puuid = puuid,
                                username = "$gameName#$tagLine",
                                tier = rankedInfo.getString("tier") ?: "bad call",
                                rank = rankedInfo.getString("rank") ?: "",
                                wins = rankedInfo.getInt("wins") ?: 0,
                                losses = rankedInfo.getInt("losses") ?: 0,
                                summonerLevel = summonerInfo.getInt("summonerLevel"),
                                summonerIcon = summonerInfo.getInt("profileIconId"),
                            )
                        )
                    }
                }
            }
        }
    }
}