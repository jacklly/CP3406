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
fun ShowBasics(
    back: () -> Unit
) {
    Card(modifier = Modifier.fillMaxSize().padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.7f))) {

        Card(modifier = Modifier.fillMaxWidth().height(60.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f)),
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
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item{
                Card(modifier = Modifier.padding(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f))) {
                    Text(text = "League: The Basics",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        color = Color.Black)
                    AsyncImage(
                        model = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ryze_5.jpg",
                        contentDescription = "Professor Ryze",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            item{
                Card(modifier = Modifier.padding(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f))) {
                    Text(text = "Farming",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        color = Color.Black)

                    Text(text = "More often called 'cs'ing'; farming is the main method of geting " +
                            "gold in a game of League.",
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        fontSize = 20.sp,
                        color = Color.Black)

                    AsyncImage(
                        model = "https://static.wikia.nocookie.net/leagueoflegends/images/e/ef/Last_Hitting.png",
                        contentDescription = "last hitting",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    Text(text = "Striking minions that walk into your lane and 'last-hitting' them" +
                            " (like above)" +
                            " gives you gold based on the type of minion. The gold you get is then" +
                            " used at spawn to buy items, making you stronger!\n" +
                            "This is most important for ADC, as they scale the best with items, " +
                            "but all roles besides support benefit greatly from having a high " +
                            "amount of minion kills (ideally 10/minute).\n\nFrom doing this, you will" +
                            " push your own wave of minions towards the enemy base, allowing you to" +
                            " pressure and even take their towers.",
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        fontSize = 20.sp,
                        color = Color.Black)

                    AsyncImage(
                        model = "https://en.number13.de/content/images/2021/03/Midlane-Tier-1-Turret-in-League-of-Legends-1.jpg",
                        contentDescription = "last hitting",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    Text(text = "Destroying these towers wins games, but be careful not to stay" +
                            " under enemy towers for too long! If you have no ally minions around " +
                            "the tower will hit you!",
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        fontSize = 20.sp,
                        color = Color.Black)
                }
            }

            item{
                Card(modifier = Modifier.padding(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f))) {
                    Text(text = "Warding",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        color = Color.Black)

                    Text(text = "Wards are small trinkets you can place on the map to gain vision of " +
                            "an area.",
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        fontSize = 20.sp,
                        color = Color.Black)

                    AsyncImage(
                        model = "https://static.wikia.nocookie.net/leagueoflegends/images/e/ef/Ward_revealing_Fog_of_War.png",
                        contentDescription = "warding",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    Text(text = "Placing wards like the one above give sight into the fog of war," +
                            " giving you a chance to see enemies coming at you. This is especially " +
                            "useful for champions in lane, as you can throw down a ward, and when " +
                            "the jungler comes to try and take you out, they won't have such a " +
                            "jump on you!",
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        fontSize = 20.sp,
                        color = Color.Black)

                }
            }

            item{
                Card(modifier = Modifier.padding(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF015482).copy(alpha = 0.7f))) {
                    Text(text = "Objectives & Grouping",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        color = Color.Black)

                    Text(text = "Grouping for objectives is another core part of League of Legends.",
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        fontSize = 20.sp,
                        color = Color.Black)

                    AsyncImage(
                        model = "https://static.wikia.nocookie.net/leagueoflegends/images/1/15/Baron_Nashor_OriginalSkin.jpg",
                        contentDescription = "Baron Nashor",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    Text(text = "Grouping up with your team to take down objectives like Baron (above)" +
                            " is crucial to winning in the long run! Some outliers can benefit from" +
                            " going solo, but generally, if you see people getting ready to " +
                            "brawl it out and take an objective, helping your team is " +
                            "the best course of action!",
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        fontSize = 20.sp,
                        color = Color.Black)
                }
            }
        }
    }
}