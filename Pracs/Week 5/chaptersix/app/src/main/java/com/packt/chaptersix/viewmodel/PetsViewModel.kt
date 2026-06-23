package com.packt.chaptersix.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.packt.chaptersix.data.NetworkResult
import com.packt.chaptersix.data.PetsRepository
import com.packt.chaptersix.views.PetsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PetsViewModel(
    private val petsRepository: PetsRepository,
): ViewModel() {
    val petsUIState = MutableStateFlow(PetsUIState())

    init {
        getPets()
    }

    fun getPets(tag: String = "cute") {
        petsUIState.update { it.copy(isLoading = true, pets = emptyList(), error = null) }
        viewModelScope.launch {
            when (val result = petsRepository.getPets(tag)) {
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