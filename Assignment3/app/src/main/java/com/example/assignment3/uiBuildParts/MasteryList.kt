package com.example.assignment3.uiBuildParts

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment3.api.getMasteryList
import com.example.assignment3.dragontailAssets.championMap
import com.example.assignment3.persistence.DatabaseProvider
import com.example.assignment3.persistence.User
import org.json.JSONArray
import org.json.JSONObject

@Composable
fun MasteryList(puuid: String) {
    val listState = rememberLazyListState()
    var masteries by remember { mutableStateOf<JSONArray?>(null) }

    masteries = getMasteryList(puuid)

    val masteryList = mutableListOf<JSONObject>()

    for (i in 0 until (masteries?.length() ?: 0)) { masteryList.add(masteries?.getJSONObject(i) ?: JSONObject()) }

    LazyColumn(state = listState) {
        this.items(masteryList) { champion ->
            //ease of access to city as string
            val currentChampionInt: Int = champion.getInt("championId")
            val currentChampionName: String? = championMap[currentChampionInt]

            Log.d(
                "CHAMPION_DEBUG",
                "ID=$currentChampionInt NAME=$currentChampionName"
            )

            Card(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = currentChampionName ?: "John Champion",
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 25.sp
                    )
                }
            }
        }
    }
}