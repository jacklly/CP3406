# 🐱 Chapter Seven - Advanced Navigation & Adaptive Layouts

Welcome to Chapter Seven! This project is the Week 6 sample app for CP3406/CP5307.

Building on the networking foundations of Chapter Six, this version focuses on **Adaptive UI Design** and **Advanced Navigation**. It demonstrates how to create a single app that feels "at home" on everything from a compact phone to a large tablet or a foldable device.

## 🎯 What this project helps you practise

This chapter introduces several critical concepts for modern Android development:

1.  **Jetpack Compose Navigation**: Moving between screens, passing complex data (JSON), and managing the backstack.
2.  **Adaptive Layouts**: Using `WindowSizeClass` to switch between Bottom Navigation (phones), Navigation Rails (foldables), and Navigation Drawers (tablets).
3.  **Canonical Layouts**: Implementing the **List-Detail** pattern, where content is displayed side-by-side on larger screens.
4.  **Foldable Support**: Detecting device postures (like "Book Mode") using the Jetpack WindowManager library.
5.  **Reactive State**: Sharing a single ViewModel across multiple navigation destinations to keep data (like favorites) in sync.

## 📱 New Features in this Chapter

*   **Multi-Tag Filtering**: You can now select multiple tags at once. The app filters for cats that match *all* selected tags.
*   **Reset Filter**: A context-aware reset button appears only when filters are active.
*   **Favorites System**: Tap the heart icon on any cat to save it. Your choices are synced across the app and viewable on a dedicated "Favorites" screen.
*   **Smart Selection**: The navigation bar, rail, and drawer automatically highlight the correct icon based on the current backstack route.

## 🧭 Suggested reading order

1.  **`MainActivity.kt`**: See how we calculate `WindowSizeClass` and `DeviceFoldPosture` to decide which navigation type to show.
2.  **`navigation/AppNavigation.kt`**: Examine the `NavHost` and see how we encode/decode `Cat` objects for safe route passing.
3.  **`views/PetListAndDetails.kt`**: This is the "Adaptive" engine. It decides whether to show a simple list or a side-by-side view.
4.  **`viewmodel/PetsViewModel.kt`**: Observe how `selectedTags` and `favoritePets` are managed in a single `StateFlow`.
5.  **`data/PetsRepositoryImpl.kt`**: Check how we join multiple tags into a single API query string.

## 🗂️ Project Structure

| File | Purpose |
|---|---|
| `MainActivity.kt` | The entry point. Handles screen size detection and top-level navigation UI. |
| `navigation/Screens.kt` | A sealed class defining all available routes in the app. |
| `navigation/AppNavigation.kt` | Defines the navigation graph and handles screen transitions. |
| `views/PetsUIState.kt` | The single source of truth for the UI, including pets, loading state, active tags, and favorites. |
| `views/PetListAndDetails.kt` | Implements the List-Detail pattern for adaptive layouts. |
| `views/FavoritePetsScreen.kt` | Displays a filtered list of cats the user has liked. |

## 📚 Key Vocabulary

| Term | What it means in this project |
|---|---|
| **WindowSizeClass** | A Material Design 3 classification (Compact, Medium, Expanded) used to build responsive UIs. |
| **Fold Posture** | The physical state of a foldable device (e.g., Closed, Half-Opened/Book, or Fully Flat). |
| **Navigation Rail** | A vertical navigation bar typically used on medium-sized screens or foldables. |
| **Modal vs. Permanent Drawer** | A drawer that slides over content (phone/tablet) vs. one that stays fixed on the side (desktop/large tablet). |
| **Uri Encoding** | The process of making JSON strings safe to use as parts of a URL/Route. |

## 🧪 Experiments for Students

Try these small tasks to deepen your understanding:

1.  **Change the Thresholds**: In `MainActivity`, try changing which `WindowWidthSizeClass` triggers the Navigation Drawer.
2.  **Add a "Clear All Favorites"**: Add a button to the `FavoritePetsScreen` that clears the entire favorites list in the ViewModel.
3.  **Empty State UI**: Modify `FavoritePetsScreen` to show a "Go Shopping" button that navigates the user back to the Home screen if their favorites list is empty.
4.  **Tag Suggestions**: Add a "Suggested Tags" row to the top of the Home screen so users don't have to find a cat to start filtering.

## 🚀 Build and Run

1.  Open in **Android Studio 2024.2.1+**.
2.  Ensure you have **SDK 35+** installed.
3.  Run on a **Phone Emulator** to see the Bottom Bar.
4.  Run on a **Tablet Emulator** (or Resizable Emulator) to see the Side Drawer and List-Detail view.

Happy coding! 🐾
