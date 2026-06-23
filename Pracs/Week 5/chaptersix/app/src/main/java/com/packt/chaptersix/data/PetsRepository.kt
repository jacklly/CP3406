package com.packt.chaptersix.data

interface PetsRepository {
    suspend fun getPets(tag: String = "cute"): NetworkResult<List<Cat>>
}