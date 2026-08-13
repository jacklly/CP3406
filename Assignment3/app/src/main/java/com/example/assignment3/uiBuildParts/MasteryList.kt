package com.example.assignment3.uiBuildParts

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.assignment3.api.getMasteryList
import com.example.assignment3.dragontailAssets.championMap
import com.example.assignment3.persistence.DatabaseProvider
import com.example.assignment3.persistence.User
import org.json.JSONArray
import org.json.JSONObject

//composable func to see champion mastery scores
@SuppressLint("DiscouragedApi")
@Composable
fun MasteryList(puuid: String) {
    val listState = rememberLazyListState()
    var masteries by remember { mutableStateOf<JSONArray?>(null) }
    val context = LocalContext.current

    masteries = getMasteryList(puuid)

    val masteryList = mutableListOf<JSONObject>()

    for (i in 0 until (masteries?.length() ?: 0)) { masteryList.add(masteries?.getJSONObject(i) ?: JSONObject()) }

    LazyColumn(state = listState) {
        this.items(masteryList) { champion ->
            //ease of access to champ as int
            val currentChampionInt: Int = champion.getInt("championId")
            val currentChampionName: String? = championMap[currentChampionInt]
            val currentChampionLevel: Int = champion.getInt("championLevel")

            Card(
                modifier = Modifier
                    .padding(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = "https://ddragon.leagueoflegends.com/cdn/16.15.1/img/champion/$currentChampionName.png",
                        contentDescription = "Item $currentChampionName",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(5.dp))
                    )

                    Text(
                        text = currentChampionName ?: "ChampNameError!",
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 25.sp,
                        color = Color.Black
                    )
                    Spacer(Modifier.weight(1f))

                    Text(
                        text = "Mastery Level: $currentChampionLevel",
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}