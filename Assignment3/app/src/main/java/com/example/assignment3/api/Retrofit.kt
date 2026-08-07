package com.example.assignment3.api

import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCall {
    private const val BASE_URL = "https://americas.api.riotgames.com/riot/"

    val api: RiotApi by lazy {
        Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RiotApi::class.java)
    }
}