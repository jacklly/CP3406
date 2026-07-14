package com.packt.chapterseven.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packt.chapterseven.data.Cat
import com.packt.chapterseven.data.NetworkResult
import com.packt.chapterseven.data.PetsRepository
import com.packt.chapterseven.views.PetsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PetsViewModel(
    private val petsRepository: PetsRepository
): ViewModel() {
    val petsUIState = MutableStateFlow(PetsUIState())

    init {
        getPets()
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            petsRepository.getFavoritePets().collect { pets ->
                petsUIState.update { it.copy(favoritePets = pets) }
            }
        }
    }

    fun toggleFavorite(cat: Cat) {
        viewModelScope.launch {
            petsRepository.toggleFavorite(cat)
        }
    }

    fun toggleTag(tag: String) {
        val currentTags = petsUIState.value.selectedTags
        val newTags = if (currentTags.contains(tag)) {
            currentTags - tag
        } else {
            currentTags + tag
        }
        getPets(newTags)
    }

    fun getPets(tags: Set<String> = emptySet()) {
        petsUIState.value = petsUIState.value.copy(isLoading = true, selectedTags = tags)
        viewModelScope.launch {
            when (val result = petsRepository.getPets(tags)) {
                is NetworkResult.Success -> {
                    petsUIState.update {
                        it.copy(isLoading = false, pets = result.data)
                    }
                }
                is NetworkResult.Error -> {
                    petsUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }
}