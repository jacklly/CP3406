package com.packt.chapterseven.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.packt.chapterseven.data.Cat

@Composable
fun PetList(
    onPetClicked: (Cat) -> Unit,
    pets: List<Cat>,
    modifier: Modifier,
    onTagClicked: (String) -> Unit,
    selectedTags: Set<String> = emptySet(),
    favoritePets: Set<Cat> = emptySet(),
    onFavoriteToggle: (Cat) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(pets) { pet ->
            PetListItem(
                cat = pet,
                onPetClicked = onPetClicked,
                onTagClicked = onTagClicked,
                selectedTags = selectedTags,
                isFavorite = favoritePets.any { it.id == pet.id },
                onFavoriteToggle = onFavoriteToggle
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PetListItem(
    cat: Cat,
    onPetClicked: (Cat) -> Unit,
    onTagClicked: (String) -> Unit,
    selectedTags: Set<String> = emptySet(),
    isFavorite: Boolean = false,
    onFavoriteToggle: (Cat) -> Unit = {}
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clickable {
                    onPetClicked(cat)
                }
        ) {
            Box {
                AsyncImage(
                    model = "https://cataas.com/cat/${cat.id}",
                    contentDescription = "Cute cat",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.FillWidth
                )
                IconButton(
                    onClick = { onFavoriteToggle(cat) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.White
                    )
                }
            }
            FlowRow(
                modifier = Modifier
                    .padding(start = 6.dp, end = 6.dp)
            ) {
                repeat(cat.tags.size) {
                    val tag = cat.tags[it]
                    val isSelected = selectedTags.contains(tag)
                    FilterChip(
                        modifier = Modifier
                            .padding(start = 3.dp, end = 3.dp),
                        onClick = { onTagClicked(tag) },
                        label = {
                            Text(text = tag)
                        },
                        selected = isSelected,
                        leadingIcon = if (isSelected) {
                            {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else null
                    )
                }
            }
        }
    }
}