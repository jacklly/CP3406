package com.packt.chapterseven.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.packt.chapterseven.data.Cat
import com.packt.chapterseven.viewmodel.PetsViewModel

@Composable
fun FavoritePetsScreen(
    petsViewModel: PetsViewModel,
    onPetClicked: (Cat) -> Unit
) {
    val uiState by petsViewModel.petsUIState.collectAsStateWithLifecycle()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Favorite Pets",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        if (uiState.favoritePets.isEmpty()) {
            Text(
                text = "You haven't favorited any cats yet!",
                modifier = Modifier.padding(top = 32.dp)
            )
        } else {
            PetList(
                onPetClicked = onPetClicked,
                pets = uiState.favoritePets.toList(),
                modifier = Modifier.fillMaxWidth(),
                onTagClicked = { tag ->
                    petsViewModel.toggleTag(tag)
                },
                selectedTags = uiState.selectedTags,
                favoritePets = uiState.favoritePets,
                onFavoriteToggle = { cat -> 
                    petsViewModel.toggleFavorite(cat)
                }
            )
        }
    }
}