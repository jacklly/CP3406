# chaptertwo — updated for current Android Studio

This copy has been modernised from the original project so that Android Studio can import and sync it using a current Android/Compose build stack.

## What changed

- Updated Android Gradle Plugin from `8.2.1` to `9.2.1`.
- Updated Gradle wrapper from `8.2` to `9.4.1`.
- Updated Kotlin from `1.8.20` to `2.3.21`.
- Added the Kotlin Compose compiler Gradle plugin: `org.jetbrains.kotlin.plugin.compose`.
- Removed the old `composeOptions { kotlinCompilerExtensionVersion ... }` setting.
- Updated compile/target SDK to `36`.
- Updated Java/Kotlin JVM target from `1.8` to `17`.
- Updated Jetpack Compose BOM to `2026.04.01`.
- Updated common AndroidX dependencies to current stable versions where verified.

## How to open

1. Extract this folder.
2. In Android Studio, choose **File → Open**.
3. Select the extracted `chaptertwo` project folder.
4. Let Android Studio install any missing SDK/build tools it prompts for.
5. Run the `app` configuration.

## Note

I could not run a full Gradle build inside this sandbox because the environment cannot download Gradle or Maven dependencies. The project files have been updated according to the current Android Gradle Plugin and Compose setup requirements.
