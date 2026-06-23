# 🐱 Week 5 Sample Project - Network Calls with Kotlin Coroutines

This project is the Week 5 sample app for CP3406 Mobile Computing and CP5307 Advanced Mobile Technology.

It demonstrates how an Android app can fetch data from the Internet, move that data through a simple app architecture, and display the result in a Jetpack Compose UI.

The sample uses the CATAAS API to fetch cat image metadata by tag. The app then displays cat images and lets you tap a tag to request a new list of cats using that tag.

## 🎯 What this project helps you practise

Use this project to understand the Week 5 focus: **network calls with Kotlin coroutines**.

As you read the code, trace this pathway:

**API response -> Kotlin data class -> repository -> ViewModel state -> Compose UI**

This is the main learning pattern for the project.

## 📱 What the app does

When the app starts:

1. `MainActivity` starts the Compose UI.
2. Koin provides a `PetsViewModel`.
3. `PetsViewModel` asks the repository for cats tagged as `cute`.
4. `PetsRepositoryImpl` makes a Retrofit request to the CATAAS API.
5. The response is mapped into Kotlin `Cat` objects using Kotlinx Serialization.
6. The result is wrapped as either `NetworkResult.Success` or `NetworkResult.Error`.
7. `PetsViewModel` updates `PetsUIState`.
8. `PetList` collects the UI state and displays loading, data, or error output.
9. Coil loads each image from the network into the Compose UI.
10. Tapping a tag requests a new cat list for that tag.

## 🧭 Suggested reading order

Read the project in this order:

1. `CatsAPI.kt` - see what request the app can make.
2. `Cat.kt` - see how the API response is represented in Kotlin.
3. `PetsRepositoryImpl.kt` - see where the network request happens.
4. `PetsViewModel.kt` - see how the app updates UI state.
5. `PetList.kt` - see how Compose displays loading, data, and error states.
6. `Modules.kt` - see how dependencies are provided by Koin.
7. `AndroidManifest.xml` - check that the app has permission to access the Internet.

## 🗂️ Main project files

| File | Purpose |
|---|---|
| `MainActivity.kt` | Starts the Compose screen and provides the top app bar. |
| `ChapterSixApplication.kt` | Starts Koin when the app launches. |
| `di/Modules.kt` | Defines Koin dependencies, including Retrofit, OkHttp, repository, dispatcher, and ViewModel. |
| `data/CatsAPI.kt` | Defines the Retrofit API request. |
| `data/Cat.kt` | Represents the JSON data returned by the API. |
| `data/NetworkResult.kt` | Represents success and error outcomes from a network call. |
| `data/PetsRepository.kt` | Defines the repository contract. |
| `data/PetsRepositoryImpl.kt` | Makes the network request on the IO dispatcher and handles errors. |
| `viewmodel/PetsViewModel.kt` | Holds UI state and launches network work in `viewModelScope`. |
| `views/PetsUIState.kt` | Stores loading, data, and error values for the UI. |
| `views/PetList.kt` | Displays loading, cat images, tags, and error messages. |
| `AndroidManifest.xml` | Includes the Internet permission needed for network access. |

## 📚 Key vocabulary

| Term | What it means in this project |
|---|---|
| API | A service the app asks for data. |
| Endpoint | The specific API path the app calls, such as `cats`. |
| Query parameter | Extra information added to the request, such as the selected tag. |
| Retrofit | The library that turns Kotlin interface functions into HTTP requests. |
| Coroutine | Kotlin's way to run work without blocking the main thread. |
| Dispatcher | Controls which thread or thread pool a coroutine uses. |
| Repository | The layer that keeps network logic away from the UI. |
| UI state | A single object that tells the screen what to show. |
| Serialisation | Converting between JSON data and Kotlin objects. |
| Coil | The library that loads images from the Internet into Compose. |
| Dependency injection | A way to provide objects, such as repositories and ViewModels, without creating them directly in the UI. |

## 🧰 Updated library and tool versions

This sample has been updated to use newer Android build tooling and library versions.

| Area | Tool or library | Version used |
|---|---|---|
| Development environment | Android Studio | `2025.3.4` |
| Android Gradle Plugin | `com.android.application` | `8.13.2` |
| Android Gradle Plugin | `com.android.library` | `8.13.2` |
| Gradle wrapper | Gradle | `8.13` |
| Kotlin | `org.jetbrains.kotlin.android` | `2.1.0` |
| Kotlin Serialization plugin | `org.jetbrains.kotlin.plugin.serialization` | `2.1.0` |
| Kotlin Compose plugin | `org.jetbrains.kotlin.plugin.compose` | `2.1.0` |
| AndroidX Core KTX | `androidx.core:core-ktx` | `1.13.1` |
| AndroidX Lifecycle | `androidx.lifecycle:lifecycle-runtime-ktx` | `2.8.7` |
| AndroidX Activity Compose | `androidx.activity:activity-compose` | `1.9.3` |
| Jetpack Compose BOM | `androidx.compose:compose-bom` | `2025.02.00` |
| Koin | `io.insert-koin:koin-core` | `4.0.0` |
| Koin Android | `io.insert-koin:koin-android` | `4.0.0` |
| Koin Compose | `io.insert-koin:koin-androidx-compose` | `4.0.0` |
| Retrofit | `com.squareup.retrofit2:retrofit` | `2.11.0` |
| Retrofit Kotlinx converter | `com.squareup.retrofit2:converter-kotlinx-serialization` | `2.11.0` |
| Kotlinx Serialization JSON | `org.jetbrains.kotlinx:kotlinx-serialization-json` | `1.7.3` |
| Kotlin Coroutines | `org.jetbrains.kotlinx:kotlinx-coroutines-core` | `1.9.0` |
| Kotlin Coroutines Android | `org.jetbrains.kotlinx:kotlinx-coroutines-android` | `1.9.0` |
| OkHttp | `com.squareup.okhttp3:okhttp` | `4.12.0` |
| Coil Compose | `io.coil-kt:coil-compose` | `2.7.0` |
| JUnit | `junit:junit` | `4.13.2` |
| AndroidX Test JUnit | `androidx.test.ext:junit` | `1.2.1` |
| Espresso | `androidx.test.espresso:espresso-core` | `3.6.1` |

## ⚙️ Android configuration

| Setting | Value |
|---|---|
| Android Studio | `2025.3.4` |
| Namespace | `com.packt.chaptersix` |
| Application ID | `com.packt.chaptersix` |
| Compile SDK | `35` |
| Target SDK | `35` |
| Minimum SDK | `24` |
| App language | Kotlin |
| UI toolkit | Jetpack Compose |
| Kotlin JVM target | `17` |

This project is written in Kotlin and uses Jetpack Compose for the UI.

## 🌐 How Retrofit is used

`CatsAPI.kt` defines the network endpoint:

```kotlin
@GET("cats")
suspend fun fetchCats(
    @Query("tags") tags: String,
    @Query("limit") limit: Int = 20,
    @Query("skip") skip: Int = 0,
): Response<List<Cat>>
```

Important points:

- `@GET("cats")` tells Retrofit which API path to call.
- `@Query("tags")` adds a query parameter to the request.
- `limit` controls how many results the app requests.
- `skip` can be used to move past earlier results.
- `suspend` means the function must be called from a coroutine or another suspend function.
- `Response<List<Cat>>` gives access to both the response body and HTTP response details.

## 🧵 How coroutines are used

`PetsViewModel` launches work in `viewModelScope`.

`PetsRepositoryImpl` uses `withContext(dispatcher)` so the network request runs on the injected dispatcher. In this project, Koin provides `Dispatchers.IO`.

This matters because network work should not block the main UI thread. If the app tried to run slow network work directly on the UI thread, the interface could freeze or feel unresponsive.

## 🔄 How UI state is handled

The UI state is stored in `PetsUIState`:

```kotlin
data class PetsUIState(
    val isLoading: Boolean = false,
    val pets: List<Cat> = emptyList(),
    val error: String? = null
)
```

The ViewModel updates this state when:

- the request starts
- the request succeeds
- the request fails

The Compose UI reads the state and decides what to show.

This gives the screen one clear source of truth for the current network state.

## 🖼️ How image loading works

The API returns cat metadata, including an `id`.

The UI builds an image URL from the cat ID and uses Coil to load the image into the Compose screen.

Coil handles the image request, loading progress, and image display.

## 🧩 How dependency injection is used

Koin is used to create and share the main app dependencies:

- `PetsRepository`
- `Dispatchers.IO`
- `PetsViewModel`
- `OkHttpClient`
- `Retrofit`
- `CatsAPI`

This keeps setup code out of the UI and makes the project easier to extend.

For example, `PetList` does not need to know how Retrofit is created. It only needs the ViewModel state that tells it what to display.

## 🔐 API safety and privacy

When you use APIs in your own app:

- Do not commit API keys, tokens, or private credentials to GitHub.
- Check what data the API collects or returns.
- Avoid requesting data your app does not need.
- Think about what the user sees if the API is unavailable.
- Use APIs that are suitable for student learning and app development practice.

This sample uses CATAAS because it is simple and does not require an API key for the demonstrated request.

## 🔍 Things to look for when studying the project

As you read the project, try to answer these questions:

- Where is the API request defined?
- Where is the JSON response mapped into Kotlin data?
- Where is the network request actually made?
- Where is the IO dispatcher used?
- Where does the app handle success and error outcomes?
- Where does the ViewModel update the UI state?
- Where does the Compose UI display loading, data, and error output?
- What happens when you tap a tag?
- How does the repository keep network logic away from the UI?
- Where is the Internet permission declared?

## 🧪 Suggested small experiments

These are learning activities, not assessment instructions.

Try one change at a time and commit your progress to GitHub:

- Change the default tag from `cute` to another tag.
- Change the request limit from `20` to a smaller number.
- Add a clearer error message in the UI.
- Add text that explains which tag is currently being displayed.
- Compare what happens with Wi-Fi on and off.
- Trace one network request from tag tap to UI update.

Run the app after each small change so you can connect the code change to the app behaviour.

## ⚠️ Known limitations of this sample

This project is designed for learning, so it keeps some things simple.

For example:

- It does not cache network results.
- It does not store cat data locally.
- It does not include paging.
- It does not include automated tests.
- It does not include advanced retry logic.
- It does not hide or secure API keys because the CATAAS example does not require one.
- It uses simple error messages rather than detailed recovery options.
- It is not intended to be copied as an assessment app.

These limitations are useful discussion points when you think about how a real app would need to be more reliable.

## 🎓 CP5307 extension thinking

For CP5307 Advanced Mobile Technology, use this sample to think beyond "does the request work?"

Consider:

- How reliable is the API?
- What happens if the API changes or becomes unavailable?
- Is the app architecture easy to test or extend?
- Does the UI handle failure in a way that supports user trust?
- What privacy or data-quality issues would matter in a more serious app?
- What trade-offs does this simple sample make for teaching purposes?

## 📝 Notes for Assessment 1

This is a sample project for learning Week 5 networking concepts. Do not copy it as your Assignment 1 app.

For your own Utility App, focus on choosing an API that genuinely supports your app purpose. Your networking choice should make sense for the problem your app is trying to solve.

You may use GenAI only to help you learn Kotlin syntax. It must not write your app, make your design or architecture decisions, or write your reflection. Any use must be acknowledged and include a Declaration of AI-Generated Material.

## 🛠️ Troubleshooting

### Gradle sync fails

Check that:

- You are using Android Studio `2025.3.4`.
- The Gradle wrapper is available.
- Android SDK 35+ is enabled in Android Studio's settings.
- You have an Internet connection so Gradle can download dependencies.

### Images do not load

Check that:

- The emulator or device has Internet access.
- The app includes the Internet permission in `AndroidManifest.xml`.
- The CATAAS API is reachable.
- The cat ID is available in the response.
- The image URL being built in the UI is valid.

### No cats appear

Check that:

- The tag being requested exists.
- The API response is successful.
- The repository returns `NetworkResult.Success`.
- `PetsUIState.pets` is being updated.
- `PetList` is collecting the ViewModel state.

### The app builds but the screen stays empty

Check that:

- The ViewModel is being provided by Koin.
- Koin is started in the application class.
- The repository is being injected into the ViewModel.
- The UI is collecting the current state from the ViewModel.

## 🚀 Build and run

Open the project in Android Studio `2025.3.4`, allow Gradle to sync, then run the app on an emulator or Android device.

Use this project as a reading and experimentation example for Week 5: **network calls with Kotlin coroutines**.
