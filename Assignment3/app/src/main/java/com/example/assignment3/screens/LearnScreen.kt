package com.example.assignment3.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.assignment3.R
import com.example.assignment3.uiBuildParts.cardPopUps.ShowAnnie
import com.example.assignment3.uiBuildParts.cardPopUps.ShowBasics
import com.example.assignment3.uiBuildParts.cardPopUps.ShowGaren
import com.example.assignment3.uiBuildParts.cardPopUps.ShowJinx
import com.example.assignment3.uiBuildParts.cardPopUps.ShowLeona
import com.example.assignment3.uiBuildParts.cardPopUps.ShowMasterYi

@Composable
fun LearnScreen(

) {
    //background set
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background3),
        contentDescription = "background for landing page",
        contentScale = ContentScale.Crop
    )

    var cardSelected by remember { mutableStateOf(false) }
    var showGaren by remember { mutableStateOf(false) }
    var showMasterYi by remember { mutableStateOf(false) }
    var showAnnie by remember { mutableStateOf(false) }
    var showJinx by remember { mutableStateOf(false) }
    var showLeona by remember { mutableStateOf(false) }
    var showBasics by remember { mutableStateOf(false) }



    if (!cardSelected) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            //Learn opener
            item {
                Card (modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                ) {
                    Text(text = "New to League?\nYou've come to the right place!",
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)
                }
            }

            item {
                Button(modifier = Modifier.fillMaxSize(),
                    onClick = { showBasics = true; cardSelected = true }) { }
            }

            //intro to map
            item {
                Card (modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                ) {
                    Text(text = "The league map (Summoner's Rift) has three lanes, and a jungle.",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = Color.Black)

                    AsyncImage(
                        model = "https://static.wikia.nocookie.net/leagueoflegends/images/d/d6/Summoner%27s_Rift_map_s14.png/revision/latest/scale-to-width-down/1000?cb=20240302220623",
                        contentDescription = "Summoner's Rift",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .clip(RoundedCornerShape(10.dp))

                    )

                    Text(text = "It's up to you where to play,\nBut each lane has a unique identity.",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = Color.Black)
                }
            }

            //top lane
            item{
                Card(modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                ) {
                    Column() {
                        Text(
                            text = "Top Lane",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        AsyncImage(
                            model = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Poppy_3.jpg",
                            contentDescription = "Splash art for top lane",
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        Text(text = "Top lane is one of the most self reliant roles in the game." +
                                "Top laners spend most of the early game isolated from the rest of" +
                                " their team, apart from the occasional gank!\nChampions in the" +
                                " top lane are usually one of three classes: bruisers, " +
                                "tanks, or skirmishers - each having defined strengths" +
                                " and weaknesses.\nThings like wave management and trading are" +
                                " most important in the top lane - so do your best to " +
                                "practice those!\n\nCheck out our recommended champion below;" +
                                " Garen!",
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth().padding(10.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { showGaren = true; cardSelected = true },
                            ) {
                                Text(text = "Garen")
                            }
                        }

                    }
                }
            }

            //jungle
            item{
                Card(modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                ) {
                    Column() {
                        Text(
                            text = "Jungle",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        AsyncImage(
                            model = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kindred_0.jpg",
                            contentDescription = "Splash art for jungle",
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        Text(text = "Jungle is the wildcard of the bunch, with assassins, tanks, " +
                                "fighters, and even supports playing in the trees between" +
                                " lanes. If you enjoy pouncing " +
                                "out from the shadows, just as the enemy laner thinks they have " +
                                "the upper hand on your teammate; Jungle is for you!" +
                                "\n\nCheck out our recommended champion below;" +
                                " Master Yi!",
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth().padding(10.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { showMasterYi = true; cardSelected = true },
                            ) {
                                Text(text = "Master Yi")
                            }
                        }

                    }
                }
            }

            //mid lane
            item{
                Card(modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                ) {
                    Column() {
                        Text(
                            text = "Mid Lane",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        AsyncImage(
                            model = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Diana_47.jpg",
                            contentDescription = "Splash art for mid lane",
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        Text(text = "Mid lane is the shortest and most central lane in League of " +
                                "Legends, making you a core early playmaker for the team." +
                                " Mid laners need the mechanics, and the macro knowledge to make " +
                                "plays not just for themselves, but those around them - so practice" +
                                " your 1v1's to get that early lead and snowball your team to " +
                                "victory.\n\nCheck out our recommended champion below;" +
                                " Annie!",
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth().padding(10.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { showAnnie = true; cardSelected = true },
                            ) {
                                Text(text = "Annie")
                            }
                        }

                    }
                }
            }

            //adc
            item{
                Card(modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                ) {
                    Column() {
                        Text(
                            text = "Bot Lane - ADC",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        AsyncImage(
                            model = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Vayne_11.jpg",
                            contentDescription = "Splash art for bot lane",
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        Text(text = "Bot lane is where you'll find a two versus two matchup!" +
                                "The ADC (AD Carry) is League's highest damage role when mastered," +
                                "but be careful - you're a glass cannon!\n" +
                                "Play around your support to survive and gather gold, then before the" +
                                " enemy realises it, you will have become the monster they never " +
                                "saw coming.\n\nCheck out our recommended champion below;" +
                                " Jinx!",
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth().padding(10.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { showJinx = true; cardSelected = true },
                            ) {
                                Text(text = "Jinx")
                            }
                        }

                    }
                }
            }

            //support
            item{
                Card(modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
                ) {
                    Column() {
                        Text(
                            text = "Bot Lane - Support",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        AsyncImage(
                            model = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Sona_9.jpg",
                            contentDescription = "Splash art for bot lane",
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                        Text(text = "The other half of the botlane; Supports protect those around" +
                                " them and make plays to setup the team. Securing vision with wards, " +
                                "crowd control, and healing/shielding are the primary objectives of " +
                                "this role.\nLike the mid laner - your teamplay is what secures the" +
                                " win, so be on the lookout to help teammates in need!" +
                                "\n\nCheck out our recommended champion below;" +
                                " Leona!",
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth().padding(10.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { showLeona = true; cardSelected = true },
                            ) {
                                Text(text = "Leona")
                            }
                        }

                    }
                }
            }
        }
    }

    //Checks for showX
    if (showBasics) {
        ShowBasics(
            back = { cardSelected = false; showGaren = false }
        )
    }
    if (showGaren) {
        ShowGaren(
            back = { cardSelected = false; showGaren = false }
        )
    }
    if (showMasterYi) {
        ShowMasterYi(
            back = { cardSelected = false; showMasterYi = false }
        )
    }
    if (showAnnie) {
        ShowAnnie(
            back = { cardSelected = false; showAnnie = false }
        )
    }
    if (showJinx) {
        ShowJinx(
            back = { cardSelected = false; showJinx = false }
        )
    }
    if (showLeona) {
        ShowLeona(
            back = { cardSelected = false; showLeona = false}
        )
    }
}