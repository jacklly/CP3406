package com.packt.chapterseven.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.packt.chapterseven.data.Cat
import com.packt.chapterseven.navigation.ContentType

@Composable
fun PetsScreenContent(
    modifier: Modifier,
    onPetClicked: (Cat) -> Unit,
    contentType: ContentType,
    petsUIState: PetsUIState,
    onTagClicked: (String) -> Unit,
    onResetClicked: () -> Unit,
    onFavoriteToggle: (Cat) -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (petsUIState.selectedTags.isEmpty()) "All Cats" else "Tags: ${petsUIState.selectedTags.joinToString(", ")}",
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
            if (petsUIState.selectedTags.isNotEmpty()) {
                Button(onClick = onResetClicked) {
                    Text("Reset")
                }
            }
        }
        
        AnimatedVisibility(
            visible = petsUIState.isLoading
        ) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(
            visible = petsUIState.pets.isNotEmpty()
        ) {
            if (contentType == ContentType.List) {
                PetList(
                    onPetClicked = onPetClicked,
                    pets = petsUIState.pets,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    onTagClicked = onTagClicked,
                    selectedTags = petsUIState.selectedTags,
                    favoritePets = petsUIState.favoritePets,
                    onFavoriteToggle = onFavoriteToggle
                )
            } else {
                PetListAndDetails(
                    pets = petsUIState.pets,
                    onTagClicked = onTagClicked,
                    selectedTags = petsUIState.selectedTags,
                    favoritePets = petsUIState.favoritePets,
                    onFavoriteToggle = onFavoriteToggle
                )
            }

        }
        AnimatedVisibility(
            visible = petsUIState.error != null
        ) {
            Text(text = petsUIState.error ?: "")
        }
    }
}