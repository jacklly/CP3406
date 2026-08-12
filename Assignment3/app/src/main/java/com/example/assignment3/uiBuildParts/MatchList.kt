package com.example.assignment3.uiBuildParts

import android.R
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.assignment3.api.getMatchIds
import com.example.assignment3.api.getMatchInfo
import com.example.assignment3.dragontailAssets.championMap


@SuppressLint("DiscouragedApi", "DefaultLocale")
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
            val outcome = matchInfo?.getString("win")
            val gameLength = matchInfo?.getInt("timePlayed")
            val damageToChamps = matchInfo?.getInt("totalDamageDealtToChampions")
            val visionScore = matchInfo?.getDouble("visionScore")
            val creepScore = matchInfo?.getDouble("totalMinionsKilled")
            val champId = matchInfo?.getInt("championId")

            //calc extra data
            val gameLengthMinutes: Int = (gameLength?.div(60) ?: 0)
            val damagePerMinute: Int? = damageToChamps?.div(gameLengthMinutes)
            val visionScorePerMinute: Double? = visionScore?.div(gameLengthMinutes)
            val csAMinute: Double? = (creepScore?.div(gameLengthMinutes))

            val visionScorePM = String.format("%.2f", visionScorePerMinute)
            val creepScorePM = String.format("%.2f", csAMinute)

            //grab champ image
            val currentChampionName: String? = championMap[champId]
            val imageRes = LocalResources.current.getIdentifier(
                currentChampionName?.lowercase() ?: "",
                "drawable", context.packageName
            )

            val itemNum0 = matchInfo?.getInt("item0") ?: 0
            val itemNum1 = matchInfo?.getInt("item1") ?: 0
            val itemNum2 = matchInfo?.getInt("item2") ?: 0
            val itemNum3 = matchInfo?.getInt("item3") ?: 0
            val itemNum4 = matchInfo?.getInt("item4") ?: 0
            val itemNum5 = matchInfo?.getInt("item5") ?: 0

            var cardColor = Color(0xFFFF6961)

            if (outcome == "true") {
                cardColor = Color(0xFF80EF80)
            }

            Log.d("item3", "$itemNum2")

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .padding(vertical = 2.5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.7f))
            ) {
                Row(modifier = Modifier.height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box (contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(cardColor)
                            .height(50.dp)
                    ) {
                        if (outcome == "true") {
                            Text(
                                text = "W",
                                modifier = Modifier
                                    .width(30.dp)
                                    .padding(horizontal = 10.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.Black
                            )
                        } else {
                            Text(
                                text = "L",
                                modifier = Modifier
                                    .width(30.dp)
                                    .padding(horizontal = 10.dp),
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
                        )
                    }

                    Spacer(Modifier.width(2.dp))

                    //damage dealt
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.height(50.dp).width(60.dp)
                    ) {
                        Image(
                            painter = painterResource(id = com.example.assignment3.R.drawable.damage),
                            contentDescription = "damage image",
                            modifier = Modifier.size(17.dp)
                        )
                        Text(text = "$damageToChamps\n$damagePerMinute/m",
                            fontSize = 15.sp,
                            color = Color.Black,
                            lineHeight = 15.sp,
                            textAlign = TextAlign.Center)
                    }

                    Spacer(Modifier.width(2.dp))

                    //creep score
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.height(50.dp).width(60.dp)
                    ) {
                        Image(
                            painter = painterResource(id = com.example.assignment3.R.drawable.creepscore),
                            contentDescription = "ward image",
                            modifier = Modifier.size(17.dp)
                        )
                        val creepScoreFin = String.format("%.0f", creepScore)
                        Text(text = "$creepScoreFin\n$creepScorePM/m",
                            fontSize = 15.sp,
                            color = Color.Black,
                            lineHeight = 15.sp,
                            textAlign = TextAlign.Center)
                    }

                    Spacer(Modifier.width(2.dp))

                    //vision score
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.height(50.dp).width(60.dp)
                    ) {
                        Image(
                            painter = painterResource(id = com.example.assignment3.R.drawable.ward),
                            contentDescription = "ward image",
                            modifier = Modifier.size(17.dp)
                        )
                        val visionScoreFin = String.format("%.0f", visionScore)
                        Text(text = "$visionScoreFin\n$visionScorePM/m",
                            fontSize = 15.sp,
                            color = Color.Black,
                            lineHeight = 15.sp,
                            textAlign = TextAlign.Center)
                    }

                    Spacer(Modifier.weight(1f))

                    //display kda info
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "$kills/$deaths/$assists",
                            color = Color.Black,
                            textAlign = TextAlign.Center)
                    }

                    Spacer(Modifier.weight(1f))

                    //display items
                    Column(modifier = Modifier.padding(bottom = 2.5.dp)) {
                        Row {
                            AsyncImage(
                                model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/item/$itemNum0.png",
                                contentDescription = "Item $itemNum0",
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(Modifier.width(5.dp))

                            AsyncImage(
                                model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/item/$itemNum1.png",
                                contentDescription = "Item $itemNum0",
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(Modifier.width(5.dp))

                            AsyncImage(
                                model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/item/$itemNum2.png",
                                contentDescription = "Item $itemNum0",
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(Modifier.width(5.dp))
                        }
                        Row(modifier = Modifier.padding(top = 2.5.dp)) {
                            AsyncImage(
                                model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/item/$itemNum3.png",
                                contentDescription = "Item $itemNum0",
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(Modifier.width(5.dp))

                            AsyncImage(
                                model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/item/$itemNum4.png",
                                contentDescription = "Item $itemNum0",
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(Modifier.width(5.dp))

                            AsyncImage(
                                model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/item/$itemNum5.png",
                                contentDescription = "Item $itemNum0",
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(Modifier.width(5.dp))
                        }
                    }
                }
            }
        }
    }
}