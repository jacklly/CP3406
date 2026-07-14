package com.packt.chapterseven.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.packt.chapterseven.data.Cat

@Composable
fun PetListAndDetails(
    pets: List<Cat>,
    onTagClicked: (String) -> Unit,
    selectedTags: Set<String> = emptySet(),
    favoritePets: Set<Cat> = emptySet(),
    onFavoriteToggle: (Cat) -> Unit = {}
) {
    var currentPet by remember(pets) {
        mutableStateOf(pets.first())
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PetList(
            onPetClicked = {
                currentPet = it
            },
            pets = pets,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onTagClicked = onTagClicked,
            selectedTags = selectedTags,
            favoritePets = favoritePets,
            onFavoriteToggle = onFavoriteToggle
        )
        PetDetailsScreenContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f),
            cat = currentPet,
            onTagClicked = onTagClicked,
            selectedTags = selectedTags,
            isFavorite = favoritePets.any { it.id == currentPet.id },
            onFavoriteToggle = onFavoriteToggle
        )
    }
}