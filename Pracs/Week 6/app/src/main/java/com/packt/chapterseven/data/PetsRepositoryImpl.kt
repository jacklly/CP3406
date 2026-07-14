package com.packt.chapterseven.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class PetsRepositoryImpl(
    private  val catsAPI: CatsAPI,
    private val dispatcher: CoroutineDispatcher
): PetsRepository {
    private val favoritePets = MutableStateFlow<Set<Cat>>(emptySet())

    override fun getFavoritePets(): Flow<Set<Cat>> = favoritePets.asStateFlow()

    override suspend fun toggleFavorite(cat: Cat) {
        favoritePets.update { current ->
            if (current.any { it.id == cat.id }) {
                current.filter { it.id != cat.id }.toSet()
            } else {
                current + cat
            }
        }
    }

    override suspend fun getPets(tags: Set<String>): NetworkResult<List<Cat>> {
        return withContext(dispatcher) {
            try {
                val tagsQuery = tags.joinToString(",")
                val response = catsAPI.fetchCats(tagsQuery)
                if (response.isSuccessful) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Error(response.errorBody()?.string() ?: "Unknown error")
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }
}