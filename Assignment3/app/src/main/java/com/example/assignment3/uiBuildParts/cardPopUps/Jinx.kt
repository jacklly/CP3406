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
fun ShowJinx(
    back: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    val champName = "Jinx"
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
                colors = CardDefaults.cardColors(containerColor = Color(0xFF7F00FF)),
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
                val champSplash = champName+"_60"
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF7F00FF).copy(alpha = 0.7f))
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF7F00FF).copy(alpha = 0.7f))
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
                                val pAbility = champName + "_Passive"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF7F00FF).copy(alpha = 0.7f)),
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
                                                text = "Get Excited!",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Whenever Jinx scores a " +
                                                        "takedown against an enemy champion, " +
                                                        "epic monster, turret, or inhibitor " +
                                                        "within 3 seconds of damaging them, " +
                                                        "she gains 175% bonus movement speed " +
                                                        "decaying over 6 seconds.\n\nAdditionally, " +
                                                        "she is allowed to exceed the attack speed " +
                                                        "cap and gains a stack of Get Excited! " +
                                                        "for the same duration, stacking up to " +
                                                        "5 times. Only takedowns against enemy " +
                                                        "champions can grant stacks beyond the " +
                                                        "first.\n\nGet Excited!: For each stack, " +
                                                        "Jinx gains 25% total attack speed, up " +
                                                        "to a maximum of 125%.",
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
                                val qAbility = champName + "Q"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF7F00FF).copy(alpha = 0.7f)),
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
                                                text = "Switcheroo!",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Toggle: Jinx switches between Pow-Pow, " +
                                                        "her minigun, and Fishbones, her " +
                                                        "rocket launcher.\n\nPow-Pow: " +
                                                        "Basic attacks with Pow-Pow generate a " +
                                                        "stack of Rev'd up for 2.5 seconds, " +
                                                        "refreshing on subsequent attacks with " +
                                                        "Pow-Pow and stacking up to 3 times. " +
                                                        "Each stack of Rev'd up grants bonus " +
                                                        "attack speed, with all stacks beyond " +
                                                        "the first one being 50% effective. " +
                                                        "Stacks expire by one when the duration " +
                                                        "ends.\n\nFishbones: Basic attacks with " +
                                                        "Fishbones cost mana on-attack to deal " +
                                                        "110% AD modified physical damage to the " +
                                                        "target and nearby enemies. The damage " +
                                                        "is affected by critical strike " +
                                                        "modifiers. While Fishbones is equipped, " +
                                                        "Jinx gains bonus range but loses 10% of " +
                                                        "her bonus attack speed.",
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
                                val wAbility = champName + "W"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF7F00FF).copy(alpha = 0.7f)),
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
                                                text = "Zap!",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Jinx fires a shock blast in the target " +
                                                        "direction that deals physical damage " +
                                                        "to the first enemy it hits and reveals " +
                                                        "and slows them for 2 seconds.",
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
                                val eAbility = champName + "E"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF7F00FF).copy(alpha = 0.7f)),
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
                                                text = "Flame Chompers!",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Jinx tosses out 3 Chompers centered at " +
                                                        "the target location, landing after 0.4 " +
                                                        "seconds, arming after 0.75 seconds, " +
                                                        "and exploding after 5 seconds to deal " +
                                                        "magic damage to nearby enemies.\n\nEach " +
                                                        "Chomper explodes on contact with an " +
                                                        "enemy champion, knocking them down " +
                                                        "and rooting them for 1.5 seconds. Enemy " +
                                                        "champions can be affected by only one Chomper.",
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
                                val eAbility = champName + "R"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF7F00FF).copy(alpha = 0.7f)),
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

                                        Text(
                                            text = "Super Mega Death Rocket!",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black,
                                            modifier = Modifier.padding(horizontal = 5.dp)
                                        )

                                        Text(
                                            text = "Jinx fires a massive rocket in the target " +
                                                    "direction, briefly granting sight of its " +
                                                    "surroundings and exploding upon colliding " +
                                                    "with an enemy champion. The explosion " +
                                                    "deals physical damage to nearby enemies " +
                                                    "and grants sight of the area for 2 seconds.",
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
