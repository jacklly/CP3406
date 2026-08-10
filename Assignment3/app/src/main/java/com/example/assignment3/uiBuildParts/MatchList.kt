package com.example.assignment3.uiBuildParts

import android.R
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment3.api.getMatchIds
import com.example.assignment3.api.getMatchInfo
import com.example.assignment3.dragontailAssets.championMap


@SuppressLint("DiscouragedApi")
@Composable
fun MatchList(puuid: String) {
    val context = LocalContext.current
    val matchList = getMatchIds(puuid)
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        this.items(matchList) { matchId ->

            //call api for given match
            val matchInfo = getMatchInfo(matchId = matchId, puuid = puuid)

            //grab important match data
            val kills = matchInfo?.getInt("kills")
            val deaths = matchInfo?.getInt("deaths")
            val assists = matchInfo?.getInt("assists")
            val lane = matchInfo?.getString("lane")
            val outcome = matchInfo?.getString("win")
            val gameLength = matchInfo?.getLong("timePlayed")
            val damageToChamps = matchInfo?.getInt("totalDamageDealtToChampions")
            val visionScore = matchInfo?.getInt("visionScore")

            //calc extra data
            val gameLengthMinutes: Long = gameLength?.div(60) ?: 0
            val damagePerMinute = damageToChamps?.div(gameLengthMinutes)
            val visionScorePerMinute = visionScore?.div(gameLengthMinutes)

            val champId = matchInfo?.getInt("championId")

            val kda: Double? =
                deaths?.let { ((assists?.let { kills?.plus(it) ?: 0.0 })?.toDouble() ?: 0.0)/it }

            //grab champ image
            val currentChampionName: String? = championMap[champId]
            val imageRes = LocalResources.current.getIdentifier(
                currentChampionName?.lowercase() ?: "",
                "drawable", context.packageName
            )

            var cardColor = Color.Red

            if (outcome == "true") {
                cardColor = Color.Green
            }

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(vertical = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cardColor.copy(alpha = 0.7f))
            ) {
                Row(modifier = Modifier.height(50.dp)) {
                    Box (contentAlignment = Alignment.Center) {
                        if (outcome == "true") {
                            Text(
                                text = "W",
                                modifier = Modifier.width(30.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                        } else {
                            Text(
                                text = "L",
                                modifier = Modifier.width(30.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                        }
                    }

                    if (imageRes != 0) {
                        Image(
                            painter = painterResource(imageRes),
                            contentDescription = "Image of $currentChampionName",
                            Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }

                    Column {
                        Image(
                            painter = painterResource(id = com.example.assignment3.R.drawable.ward),
                            contentDescription = "ward image",
                            modifier = Modifier.size(10.dp)
                        )
                        Text(text = "Vision Score: $visionScore")
                        Text(text = "$visionScorePerMinute/min")
                    }

                    Column {
                        Image(
                            painter = painterResource(id = com.example.assignment3.R.drawable.damage),
                            contentDescription = "damage image",
                            modifier = Modifier.size(10.dp)
                        )
                        Text(text = "Damage: $damageToChamps")
                        Text(text = "$damagePerMinute/min")
                    }

                    Column() {
                        Text(text = "$kills / $deaths / $assists")
                        Text(text = "$kda")
                    }

                    Text(text = "$lane")
                }
            }
        }
    }
}