# Utility App – CP3406 / CP5307 - Jack Kelly

My simple utility app design, made using **Jetpack Compose** and **Material Design 3**.

---

### How to Run
1. Clone or download this repo  
2. Open in Android Studio  
3. Run on an emulator or physical device (API 26+ recommended)  

---

## Composables

### UtilityApp()
- Nests variables to be passed between Viewmodels
- Contains the screen layout using a Scaffold
- Toggles content between Utility and Settings

### HomeScreen.kt / HomeScreen()
- Provides 'at a glance' weather information
- Calls a weather API for data
- Displays multiple Cards that hold said data in a clear manner

### SettingScreen.kt / SettingsScreen()
- Holder for user preference on city
- Allows for choice between multiple Queensland locations
- Changes behaviour of app

---

## Key Concepts Covered

| Week | Concept                        |
|------|--------------------------------|
| 1    | Kotlin + Android Studio        |
| 2    | Jetpack Compose Layouts        |
| 3    | Material Design 3              |
| 4    | ViewModel                      |
| 5    | Retrofit                       |

---

## 📚 License
This is based on the provided template for educational use in CP3406.  
