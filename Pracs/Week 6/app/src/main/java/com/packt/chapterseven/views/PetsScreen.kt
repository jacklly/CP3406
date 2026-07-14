package com.packt.chapterseven.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.packt.chapterseven.data.Cat
import com.packt.chapterseven.navigation.ContentType
import com.packt.chapterseven.viewmodel.PetsViewModel

@Composable
fun PetsScreen(
    onPetClicked: (Cat) -> Unit,
    contentType: ContentType,
    petsViewModel: PetsViewModel
) {
    val petsUIState by petsViewModel.petsUIState.collectAsStateWithLifecycle()
    PetsScreenContent(
        modifier = Modifier
            .fillMaxSize(),
        onPetClicked = onPetClicked,
        contentType = contentType,
        petsUIState = petsUIState,
        onTagClicked = { tag ->
            petsViewModel.toggleTag(tag)
        },
        onResetClicked = {
            petsViewModel.getPets(emptySet())
        },
        onFavoriteToggle = { cat ->
            petsViewModel.toggleFavorite(cat)
        }
    )
}