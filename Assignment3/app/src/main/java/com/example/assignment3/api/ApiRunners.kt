package com.example.assignment3.api

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject

val apiKey = ApiKey().loadKey()

//Get users puuid
@Composable
fun getPuuidCall(
    gameName: String,
    tagLine: String
): String {
    var validPuuid by remember { mutableStateOf<String>("") }

    LaunchedEffect(gameName, tagLine) {
        val puuid = RetrofitCall.api.riotApi("account/v1/accounts/by-riot-id/$gameName/$tagLine?api_key=$apiKey")

        val puuidString: String = puuid.string()

            if (puuidString != "") {
                val puuidJson = JSONObject(puuidString)
                validPuuid = puuidJson.getString("puuid")
            }
        }
    return validPuuid
}

//get users 10 champion mastery
@Composable
fun getMasteryList(
    puuid: String
): JSONArray? {
    var fullList by remember { mutableStateOf<JSONArray?>(null) }
    val cleanPuuid = puuid.trim()

    LaunchedEffect(cleanPuuid) {
        val masteryList = RetrofitCall.api.riotApi(
            "https://oc1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/$puuid/top?count=10&api_key=$apiKey")

        val apiString = masteryList.string()

        fullList = JSONArray(apiString)
    }

    return fullList
}

@Composable
fun getSummonerLevel(
    puuid: String
): Int {
    var summonerLevel by remember { mutableStateOf(0) }
    LaunchedEffect(puuid) {
        val apiCall = RetrofitCall.api.riotApi(
            "https://oc1.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/$puuid?api_key=$apiKey")

        val apiString = apiCall.string()
        val apiJSON = JSONObject(apiString)

        summonerLevel = apiJSON.getInt("summonerLevel")
    }
    return summonerLevel
}

@Composable
fun getMatchIds(puuid: String): List<String> {

    var matchIdList by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(puuid) {
        val apiCall = RetrofitCall.api.riotApi(
            "https://sea.api.riotgames.com/lol/match/v5/matches/by-puuid/$puuid/ids?start=0&count=20&api_key=$apiKey")

        val apiString = apiCall.string()
        val apiJSON = JSONArray(apiString)
        val ids = mutableListOf<String>()

        for (i in 0 until apiJSON.length()) { ids.add(apiJSON.getString(i)) }

        matchIdList = ids
    }

    return matchIdList
}

@Composable
fun getMatchInfo(matchId: String, puuid: String): JSONObject? {

    var playerData by remember { mutableStateOf<JSONObject?>(null)}

    LaunchedEffect(matchId, puuid) {
        val apiCall = RetrofitCall.api.riotApi(
            "https://sea.api.riotgames.com/lol/match/v5/matches/$matchId?api_key=$apiKey")

        val apiString = apiCall.string()
        val apiJSON = JSONObject(apiString)
        val info = apiJSON.getJSONObject("info")
        val participants = info.getJSONArray("participants")

        for (i in 0 until participants.length()) {

            val participant = participants.getJSONObject(i)

            if (participant.getString("puuid") == puuid) {
                playerData = participant
                break
            }
        }
    }
    return playerData
}

@Composable
fun getRank(puuid: String): JSONObject? {
    var result by remember { mutableStateOf<JSONObject?>(null) }

    LaunchedEffect(puuid) {
        val apiCall = RetrofitCall.api.riotApi(
            "https://oc1.api.riotgames.com/lol/league/v4/entries/by-puuid/$puuid?api_key=$apiKey"
        )
        val apiString = apiCall.string()
        val apiJSON = JSONArray(apiString)

        val data = mutableListOf<String>()

        for (i in 0 until apiJSON.length()) {
            val set = apiJSON.getJSONObject(i)
            val queue = set.getString("queueType")

            //get soloqueue data
            if (queue == "RANKED_SOLO_5x5") {
                result = set
                break
            }
        }
    }
    return result
}