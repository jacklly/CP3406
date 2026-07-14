package com.packt.chapterseven.data

import kotlinx.coroutines.flow.Flow

interface PetsRepository {
    suspend fun getPets(tags: Set<String> = emptySet()): NetworkResult<List<Cat>>
    fun getFavoritePets(): Flow<Set<Cat>>
    suspend fun toggleFavorite(cat: Cat)
}