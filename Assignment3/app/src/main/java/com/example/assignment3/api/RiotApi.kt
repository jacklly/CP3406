package com.example.assignment3.api

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Url

interface RiotApi {
    @GET
    suspend fun riotApi(
        @Url url: String
    ): ResponseBody
}