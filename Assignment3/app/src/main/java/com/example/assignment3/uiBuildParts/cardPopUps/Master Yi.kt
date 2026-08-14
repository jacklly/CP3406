package com.example.assignment3.uiBuildParts.cardPopUps

import android.graphics.Paint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.assignment3.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.intellij.lang.annotations.JdkConstants
import org.json.JSONObject
import java.net.URL

@Composable
fun ShowMasterYi(
    back: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    val champName = "MasterYi"
    var champInfoString by remember { mutableStateOf("") }
    var champBlurb by remember {mutableStateOf("")}
    var champLore by remember {mutableStateOf("")}

    //call website to get info on champ
    LaunchedEffect(champName) {
        champInfoString = withContext(Dispatchers.IO) {
            URL("https://ddragon.leagueoflegends.com/cdn/16.15.1/data/en_AU/champion/$champName.json").readText()
        }
    }

    //check that the infostring is filled
    if (champInfoString != "") {
        //get info from the JSON
        val champInfoJson = JSONObject(champInfoString)
        val champInfo = champInfoJson.getJSONObject("data")
        val champData = champInfo.getJSONObject(champName)
        champBlurb = champData.getString("title")
        champLore = champData.getString("lore")
    }

    if (isVisible) {

        Card(modifier = Modifier.fillMaxSize().padding(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.7f))) {

            Card(modifier = Modifier.fillMaxWidth().height(60.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF32CD32)),
                shape = RectangleShape) {
                Row {
                    IconButton(
                        onClick = { back() },
                        modifier = Modifier.size(50.dp).padding(5.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_arrow_back_24),
                            contentDescription = "Back Icon",
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    AsyncImage(
                        model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/champion/$champName.png",
                        contentDescription = "Icon of $champName",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(5.dp)
                            .clip(RoundedCornerShape(5.dp))
                    )
                }
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                val champSplash = champName+"_52"
                item {
                    AsyncImage(
                        model = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/$champSplash.jpg",
                        contentDescription = "Splash art of $champName",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Card (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .padding(horizontal = 10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF32CD32).copy(alpha = 0.7f))
                    ) {
                        Row(horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "$champName: $champBlurb",
                                fontSize = 30.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                item {
                    Text(text = champLore,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 20.sp,
                        color = Color.Black)

                }

                item {
                    Card (
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF32CD32).copy(alpha = 0.7f))
                    ) {
                        Text(
                            text = "Abilities",
                            fontSize = 25.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                item {
                    Column() {
                        //Passive
                        Card (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .padding(horizontal = 10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                        ) {
                            Row() {
                                val pAbility = champName + "_Passive1"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF32CD32).copy(alpha = 0.7f)),
                                        shape = RectangleShape)
                                    {
                                        Text(
                                            text = "Passive",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Row {
                                        AsyncImage(
                                            model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/passive/$pAbility.png",
                                            contentDescription = "Icon of $pAbility",
                                            modifier = Modifier
                                                .size(90.dp)
                                                .padding(5.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        )
                                        Column() {
                                            Text(
                                                text = "Double Strike",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Master Yi's basic attacks on-hit generate " +
                                                        "a stack of Double Strike for 4 seconds, " +
                                                        "refreshing on subsequent hits and " +
                                                        "stacking up to 3 times. At 3 stacks, " +
                                                        "Master Yi's next basic attack on-attack " +
                                                        "is empowered to consume the stacks to " +
                                                        "strike twice, the second strike dealing " +
                                                        "50% AD physical damage.\n\n" +
                                                        "The second strike applies on-hit and " +
                                                        "on-attack effects at 100% effectiveness " +
                                                        "and is affected by critical strike modifiers.",
                                                fontSize = 15.sp,
                                                color = Color.Black,
                                                lineHeight = 15.sp,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        //Q ability
                        Card (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .padding(horizontal = 10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                        ) {
                            Row() {
                                val qAbility = "AlphaStrike"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF32CD32).copy(alpha = 0.7f)),
                                        shape = RectangleShape)
                                    {
                                        Text(
                                            text = "Q Ability",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Row() {
                                        Column {
                                            AsyncImage(
                                                model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/spell/$qAbility.png",
                                                contentDescription = "Icon of $qAbility",
                                                modifier = Modifier
                                                    .size(90.dp)
                                                    .padding(5.dp)
                                                    .clip(RoundedCornerShape(10.dp))
                                            )
                                        }
                                        Column() {
                                            Text(
                                                text = "Alpha Strike",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Master Yi vanishes and becomes unable " +
                                                        "to act. After 0.231 seconds, he marks " +
                                                        "the target enemy and then proceeds to " +
                                                        "mark the nearest visible un-marked " +
                                                        "enemy within 600 units, recurring every " +
                                                        "0.231 seconds up to 3 times. If there " +
                                                        "are no other un-marked targets before " +
                                                        "then, Master Yi can mark the same " +
                                                        "enemies again. During Alpha Strike, " +
                                                        "Master Yi can select a direction from " +
                                                        "the primary target.\n\nUpon finishing " +
                                                        "marking, Master Yi reappears 75 units " +
                                                        "in the targeted direction, or in front " +
                                                        "of the target otherwise, and then " +
                                                        "becomes able to act again after " +
                                                        "0.165 seconds. If the primary target " +
                                                        "dies or is too far away during the " +
                                                        "delay, Master Yi will reappear at the " +
                                                        "initial cast location instead.\n\n" +
                                                        "Master Yi then detonates the marks, " +
                                                        "dealing physical damage and applying " +
                                                        "on-hit effects at 65% effectiveness. " +
                                                        "Marks after the first on the same " +
                                                        "target instantly deal 25% damage and " +
                                                        "apply On-hit icon on-hit effects at " +
                                                        "16.25% effectiveness. Alpha Strike " +
                                                        "deals bonus physical damage to " +
                                                        "monsters per hit.",
                                                fontSize = 15.sp,
                                                color = Color.Black,
                                                lineHeight = 15.sp,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        //W ability
                        Card (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .padding(horizontal = 10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                        ) {
                            Row() {
                                val wAbility = "Meditate"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF32CD32).copy(alpha = 0.7f)),
                                        shape = RectangleShape)
                                    {
                                        Text(
                                            text = "W Ability",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Row() {
                                        AsyncImage(
                                            model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/spell/$wAbility.png",
                                            contentDescription = "Icon of $wAbility",
                                            modifier = Modifier
                                                .size(90.dp)
                                                .padding(5.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        )
                                        Column() {
                                            Text(
                                                text = "Meditate",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Master Yi channels for up to 4 seconds, " +
                                                        "healing himself every 0.5 seconds, " +
                                                        "increased by 0% − 100% (based on " +
                                                        "missing health).\n\nWhile channeling, " +
                                                        "Master Yi gains 70% damage reduction " +
                                                        "for the first 0.5 seconds, which is " +
                                                        "then modified to a reduced amount for " +
                                                        "the remaining duration of the channel. " +
                                                        "Meditate's damage reduction is halved " +
                                                        "against turrets and lingers for 0.5 " +
                                                        "seconds after the channel ends.",
                                                fontSize = 15.sp,
                                                color = Color.Black,
                                                lineHeight = 15.sp,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        //E ability
                        Card (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .padding(horizontal = 10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                        ) {
                            Row() {
                                val eAbility = "WujuStyle"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF32CD32).copy(alpha = 0.7f)),
                                        shape = RectangleShape)
                                    {
                                        Text(
                                            text = "E Ability",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Row() {
                                        AsyncImage(
                                            model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/spell/$eAbility.png",
                                            contentDescription = "Icon of $eAbility",
                                            modifier = Modifier
                                                .size(90.dp)
                                                .padding(5.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        )
                                        Column() {
                                            Text(
                                                text = "Wuju Style",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Master Yi empowers his basic attacks " +
                                                        "within the next 5 seconds to deal " +
                                                        "bonus true damage on-hit.",
                                                fontSize = 15.sp,
                                                color = Color.Black,
                                                lineHeight = 15.sp,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        //R ability
                        Card (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .padding(horizontal = 10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                        ) {
                            Row() {
                                val eAbility = "Highlander"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF32CD32).copy(alpha = 0.7f)),
                                        shape = RectangleShape)
                                    {
                                        Text(
                                            text = "R Ability",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Row() {
                                        AsyncImage(
                                            model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/spell/$eAbility.png",
                                            contentDescription = "Icon of $eAbility",
                                            modifier = Modifier
                                                .size(90.dp)
                                                .padding(5.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        )
                                        Column() {
                                            Text(
                                                text = "Highlander",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Master Yi cleanses himself from all " +
                                                        "slows and cripples. For the next 7 " +
                                                        "seconds, he gains ghosting, bonus " +
                                                        "attack speed, bonus movement speed, " +
                                                        "slow immunity, and cripple immunity.",
                                                fontSize = 15.sp,
                                                color = Color.Black,
                                                lineHeight = 15.sp,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}