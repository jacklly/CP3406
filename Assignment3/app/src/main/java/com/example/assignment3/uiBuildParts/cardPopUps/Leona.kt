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
fun ShowLeona(
    back: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    val champName = "Leona"
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
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD4A017)),
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
                val champSplash = champName+"_10"
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFD4A017).copy(alpha = 0.7f))
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFD4A017).copy(alpha = 0.7f))
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
                                val pAbility = champName + "Sunlight"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFD4A017).copy(alpha = 0.7f)),
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
                                                text = "Sunlight",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Leona's abilities mark enemies hit for 1.5 seconds, " +
                                                        "refreshing on subsequent hits.\n\n" +
                                                        "Allied Champion icon champions' damaging attacks and " +
                                                        "abilities against a marked target will consume the " +
                                                        "mark to deal 32 − 151 (based on level) bonus magic damage.",
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
                                val qAbility = champName + "ShieldOfDaybreak"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFD4A017).copy(alpha = 0.7f)),
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
                                        AsyncImage(
                                            model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/spell/$qAbility.png",
                                            contentDescription = "Icon of $qAbility",
                                            modifier = Modifier
                                                .size(90.dp)
                                                .padding(5.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        )
                                        Column() {
                                            Text(
                                                text = "Shield of Daybreak",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Leona illuminates her shield, empowering her next " +
                                                        "basic attack within 6 seconds to have a 0.25-second cast" +
                                                        " time, gain 50 bonus range, deal bonus magic damage and" +
                                                        " stun the target for 1 second.",
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
                                val wAbility = champName + "SolarBarrier"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFD4A017).copy(alpha = 0.7f)),
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
                                                text = "Eclipse",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Leona raises her guard for 3 seconds, " +
                                                        "gaining flat damage reduction of up to " +
                                                        "50% of the damage instance and Attack " +
                                                        "damage icon bonus armor and Attack damage " +
                                                        "icon bonus magic resistance.\n\nHer " +
                                                        "shield detonates after the duration, " +
                                                        "dealing magic damage to nearby enemies. " +
                                                        "If this hits an enemy, Leona's guard is " +
                                                        "extended for an additional 3 seconds.",
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
                                val eAbility = champName + "ZenithBlade"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFD4A017).copy(alpha = 0.7f)),
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
                                                text = "Zenith Blade",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Leona projects her sword in the target " +
                                                        "direction that deals magic damage to " +
                                                        "enemies hit.\n\nIf she hits at least " +
                                                        "one enemy Champion icon champion, she " +
                                                        "will Dash dash 225 units behind the " +
                                                        "last one struck within 3000 range " +
                                                        "and root them for 0.5 seconds.",
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
                                val eAbility = champName + "SolarFlare"
                                Column() {
                                    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFD4A017).copy(alpha = 0.7f)),
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
                                                text = "Solar Flare",
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            )
                                            Text(
                                                text = "Leona calls down a solar flare that " +
                                                        "strikes upon the target location after " +
                                                        "0.625 seconds, granting sight of the " +
                                                        "area before impact and for another 3 " +
                                                        "seconds afterwards. Enemies hit are " +
                                                        "dealt magic damage.\n\nTargets are " +
                                                        "also slowed by 80% for 1.75 seconds, " +
                                                        "or stunned for the same duration if " +
                                                        "they are hit by the epicenter.",
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