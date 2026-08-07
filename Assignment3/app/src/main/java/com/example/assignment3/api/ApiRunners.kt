package com.example.assignment3.api

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import okhttp3.ResponseBody
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
        val puuid = RetrofitCall.api.getPuuid("account/v1/accounts/by-riot-id/$gameName/$tagLine?api_key=$apiKey")

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
): List<JSONObject> {
    var masteryList by remember { mutableStateOf(emptyList<JSONObject>()) }

    LaunchedEffect(puuid) {
        masteryList = RetrofitCall.api.getMasteryList(
            "https://oc1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/$puuid/top?count=10&api_key=$apiKey")
    }
    return masteryList
}