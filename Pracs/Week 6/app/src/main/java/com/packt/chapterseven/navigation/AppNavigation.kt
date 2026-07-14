package com.packt.chapterseven.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.packt.chapterseven.data.Cat
import com.packt.chapterseven.viewmodel.PetsViewModel
import com.packt.chapterseven.views.FavoritePetsScreen
import com.packt.chapterseven.views.PetDetailsScreen
import com.packt.chapterseven.views.PetsScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel


@Composable
fun AppNavigation(
    contentType: ContentType,
    navHostController: NavHostController = rememberNavController(),
    petsViewModel: PetsViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.PetsScreen.route
    ) {
        composable(Screens.PetsScreen.route) {
            PetsScreen(
                onPetClicked = { cat ->
                    navHostController.navigate(
                        "${Screens.PetDetailsScreen.route}/${Uri.encode(Json.encodeToString(cat))}"
                    )
                },
                contentType = contentType,
                petsViewModel = petsViewModel
            )
        }
        composable(
            route = "${Screens.PetDetailsScreen.route}/{cat}",
            arguments = listOf(
                navArgument("cat") {
                    type = NavType.StringType
                }
            )
        ) {
            val petsUIState = petsViewModel.petsUIState.collectAsStateWithLifecycle().value
            val cat = Json.decodeFromString<Cat>(Uri.decode(it.arguments?.getString("cat") ?: ""))
            PetDetailsScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                },
                cat = cat,
                onTagClicked = { tag ->
                    petsViewModel.toggleTag(tag)
                    navHostController.popBackStack(Screens.PetsScreen.route, false)
                },
                selectedTags = petsUIState.selectedTags,
                isFavorite = petsUIState.favoritePets.any { it.id == cat.id },
                onFavoriteToggle = { currentCat -> petsViewModel.toggleFavorite(currentCat) }
            )
        }
        composable(Screens.FavoritePetsScreen.route) {
            FavoritePetsScreen(petsViewModel = petsViewModel, onPetClicked = { cat ->
                navHostController.navigate(
                    "${Screens.PetDetailsScreen.route}/${Uri.encode(Json.encodeToString(cat))}"
                )
            })
        }
    }
}