package com.example.assignment3.uiBuildParts

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.assignment3.api.getMatchIds
import com.example.assignment3.api.getMatchInfo

@Composable
fun MatchList(puuid: String) {

    val matchList = getMatchIds(puuid)
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        this.items(matchList) { matchId ->

            val matchInfo = getMatchInfo(matchId = matchId, puuid = puuid)

            val spellCasts = matchInfo?.getInt("spell1Casts")

            Text(text = spellCasts.toString())


            Card() { }
        }
    }
}