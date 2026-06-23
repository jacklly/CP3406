package com.packt.chaptersix.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.packt.chaptersix.data.Cat
import com.packt.chaptersix.viewmodel.PetsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PetList(modifier: Modifier) {
    val petsViewModel: PetsViewModel = koinViewModel()
    val petsUIState by petsViewModel.petsUIState.collectAsState()

    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (petsUIState.isLoading) {
            CircularProgressIndicator()
        }

        if (petsUIState.pets.isNotEmpty()) {
            LazyColumn {
                items(petsUIState.pets, key = { it.id }) { pet ->
                    PetListItem(
                        cat = pet,
                        onTagClick = { tag ->
                            petsViewModel.getPets(tag)
                        }
                    )
                }
            }
        }
        if (petsUIState.error != null) {
            Text(text = petsUIState.error ?: "")
        }
    }
}

@Composable
fun PetListItem(cat: Cat, onTagClick: (String) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cataas.com/cat/${cat.id}?width=500")
                    .crossfade(enable = true)
                    .build(),
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp)
                        )
                    }
                },
                contentDescription = "Cute cat",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .padding(start = 6.dp, end = 6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                cat.tags.chunked(3).forEach { rowTags ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        rowTags.forEach { tag ->
                            SuggestionChip(
                                onClick = { onTagClick(tag) },
                                label = {
                                    Text(text = tag)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}