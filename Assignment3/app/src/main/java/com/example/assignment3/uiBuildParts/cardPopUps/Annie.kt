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
fun ShowAnnie(
    back: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    val champName = "Annie"
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
                colors = CardDefaults.cardColors(containerColor = Color(0xFFDC143C)),
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
                val champSplash = champName+"_50"
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFDC143C).copy(alpha = 0.7f))
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFDC143C).copy(alpha = 0.7f))
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
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFDC143C).copy(alpha = 0.7f)),
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
                                                text = "Pyromania",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Annie generates a stack of Pyromania " +
                                                        "whenever she hits an enemy with " +
                                                        "Disintegrate or casts her other " +
                                                        "abilities, stacking up to 4 times, at " +
                                                        "which she gains Energized.\n\n" +
                                                        "Energized: Annie empowers her next " +
                                                        "cast of Disintegrate, Incinerate, " +
                                                        "or Summon: Tibbers to consume all " +
                                                        "Pyromania stacks to stun enemies hit for " +
                                                        "1.25 / 1.5 / 1.75 (based on level) seconds.",
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
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFDC143C).copy(alpha = 0.7f)),
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
                                                text = "Disintegrate",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Annie hurls a fireball at the target " +
                                                        "enemy that deals magic damage.\n\n" +
                                                        "If this kills the target, 50% of the " +
                                                        "cooldown is reduced and the mana cost " +
                                                        "is refunded.",
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
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFDC143C).copy(alpha = 0.7f)),
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
                                                text = "Incinerate",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Annie releases fire in a cone in the " +
                                                        "target direction, dealing magic damage " +
                                                        "to enemies hit.",
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
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFDC143C).copy(alpha = 0.7f)),
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
                                                text = "Molten Shield",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Annie grants herself or the target " +
                                                        "allied champion and Tibbers a shield " +
                                                        "for 3 seconds and 20% − 50% " +
                                                        "(based on level) bonus movement speed " +
                                                        "that decays over 1.5 seconds.\n\n" +
                                                        "While Molten Shield is active, enemies " +
                                                        "that deal damage to it take magic damage. " +
                                                        "This may only occur once per enemy per " +
                                                        "cast for each active Molten Shield.",
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
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFDC143C).copy(alpha = 0.7f)),
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
                                                text = "Summon: Tibbers",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Annie summons Tibbers to the target " +
                                                        "location in a burst of flame, dealing " +
                                                        "magic damage to enemies near him. " +
                                                        "Summon: Tibbers can be recast at any " +
                                                        "time while Tibbers is alive.\n\n" +
                                                        "Recast: Annie directs Tibbers to the " +
                                                        "target location.",
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