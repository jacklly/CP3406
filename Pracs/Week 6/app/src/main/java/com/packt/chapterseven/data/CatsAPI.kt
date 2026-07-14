package com.packt.chapterseven.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsAPI {
    @GET("cats")
    suspend fun fetchCats(
        @Query("tags") tags: String,
        @Query("limit") limit: Int = 20,
        @Query("skip") skip: Int = 0,
    ): Response<List<Cat>>
}