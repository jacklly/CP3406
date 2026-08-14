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
fun ShowGaren(
    back: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    val champName = "Garen"
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
                colors = CardDefaults.cardColors(containerColor = Color(0xFF015482)),
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
                val champSplash = champName+"_13"

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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f))
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f))
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
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f)),
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
                                                text = "Perseverance",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Garen regenerates 1.5% − 10.1% (based " +
                                                        "on level) of his maximum health every " +
                                                        "5 seconds.",
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
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f)),
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
                                                text = "Decisive Strike",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Garen cleanses himself of all slows " +
                                                        "and gains 35% bonus movement speed " +
                                                        "for a duration.\n\nAdditionally, " +
                                                        "Garen empowers his next basic attack " +
                                                        "within 4.5 seconds to have an " +
                                                        "uncancellable windup, lunge at the " +
                                                        "target, deal bonus physical damage, " +
                                                        "and silence them for 1.5 seconds.",
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
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f)),
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
                                                text = "Courage",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Passive: Whenever Garen kills an enemy, " +
                                                        "he generates a stack of Courage, " +
                                                        "stacking up to 150 times.\n\nCourage: " +
                                                        "For each stack, Garen gains 0.2 " +
                                                        "bonus armor and 0.2 bonus magic " +
                                                        "resistance, up to a maximum of 30 " +
                                                        "bonus resistances each.\n\n" +
                                                        "Active: Garen reduces incoming " +
                                                        "damage for 4 seconds.",
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
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f)),
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
                                                text = "Judgement",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Garen rapidly spins his sword around " +
                                                        "himself 7 (+ 1 per 25% bonus attack " +
                                                        "speed) times over 3 seconds, becoming " +
                                                        "unable to declare basic attacks but " +
                                                        "gaining ghosting and dealing physical " +
                                                        "damage to nearby enemies periodically. " +
                                                        "Judgment can be recast after 1 second " +
                                                        "while active, and does so automatically " +
                                                        "after it ends.\n\nRecast: Garen ends " +
                                                        "Judgment prematurely.",
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
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f)),
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
                                                text = "Demacian Justice",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Garen calls upon the might of Demacia " +
                                                        "onto the target enemy champion, dealing " +
                                                        "them true damage as well as revealing " +
                                                        "them for 1 second at the start of the " +
                                                        "cast time.",
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