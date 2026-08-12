package com.example.assignment3.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
    var tier by remember { mutableStateOf("") }
    var rank by remember { mutableStateOf("") }
    var wins by remember { mutableIntStateOf(0) }
    var losses by remember { mutableIntStateOf(0) }
    var summonerIcon by remember { mutableIntStateOf(0) }
    var username by remember { mutableStateOf("") }
    var summonerLevel by remember { mutableIntStateOf(0) }

    //grab puuid
    LaunchedEffect(Unit) {
        scope.launch {
            val user = db.userDao().getUser()
            puuid = user?.puuid ?: ""
            tier = user?.tier ?: ""
            rank = user?.rank ?: ""
            wins = user?.wins ?: 0
            losses = user?.losses ?: 0
            summonerIcon = user?.summonerIcon ?: 0
            username = user?.username ?: ""
            summonerLevel = user?.summonerLevel ?: 0
        }
    }

    //Card
    if (puuid != "") {
        Column(modifier = Modifier.fillMaxSize()) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp).padding(top = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
            ) {
                Column(modifier = Modifier.height(200.dp).fillMaxWidth()) {
                    Row (modifier = Modifier.height(100.dp)){
                        //Display summoner icon
                        Box(modifier = Modifier.padding(5.dp)) {
                            AsyncImage(
                                model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/profileicon/$summonerIcon.png",
                                contentDescription = "summoner icon No. $summonerIcon",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(15.dp))
                            )
                        }

                        Column(
                            modifier = Modifier.height(110.dp),
                            verticalArrangement = Arrangement.Center
                        )
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
                    Row (modifier = Modifier.height(100.dp).fillMaxWidth()) {
                        Row () {
                            Spacer(modifier = Modifier.width(5.dp))

                            //grab lowercase of ranked tier for url
                            val rankedTier = tier.lowercase()

                            //display ranked tier emblem
                            Box(modifier = Modifier
                                .size(100.dp)
                                .graphicsLayer(clip = false)
                                .padding(5.dp)
                            ) {
                                AsyncImage(
                                    model = "https://raw.communitydragon.org/14.10/plugins/rcp-fe-lol-static-assets/global/default/images/ranked-emblem/emblem-$rankedTier.png",
                                    contentDescription = "ranked tier display",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(15.dp))
                                        .scale(3f)
                                )
                            }

                            Column (modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "$tier $rank",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.Black
                                )
                                Text(text = "Wins: $wins")
                                Text(text = "Losses: $losses")
                            }
                        }

                    }
                }
            }
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp).padding(vertical = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))) {
                Text(
                    text = "Match History",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            MatchList(puuid)
        }
    }
}