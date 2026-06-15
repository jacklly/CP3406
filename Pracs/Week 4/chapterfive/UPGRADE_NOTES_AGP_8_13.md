# Upgrade notes: Android Studio / AGP 8.13.x

This project has been updated from the older Android Gradle Plugin / Kotlin / Compose setup to a configuration suitable for Android Studio using AGP 8.13.x.

The update follows the same approach used in the previously updated `chapterfour` project, while preserving this project's Kotlin DSL Gradle files and Koin-based architecture example.

## Main changes

- Updated Android Gradle Plugin from `8.2.1` to `8.13.2`.
- Updated Gradle wrapper distribution from `8.2` to `8.13`.
- Updated Kotlin Gradle plugin from `1.8.20` to `2.3.21`.
- Added the Kotlin Compose Compiler Gradle plugin:
  - `org.jetbrains.kotlin.plugin.compose` version `2.3.21`
- Removed the old Compose compiler extension setting:
  - `composeOptions { kotlinCompilerExtensionVersion = "1.4.6" }`
- Updated compile/target SDK to `36`.
- Updated Java/Kotlin bytecode target from `1.8` to `17`.
- Updated Compose BOM to `2026.05.00`.
- Updated AndroidX core, lifecycle, activity-compose, AndroidX test JUnit, and Espresso versions to match the Chapter 4 update style.
- Replaced the older `packagingOptions` block with the current `packaging` DSL.
- Updated the TopAppBar colours call from `TopAppBarDefaults.smallTopAppBarColors` to `TopAppBarDefaults.topAppBarColors`.

## Notes

- Android Studio should use JDK 17 or newer for this project. The bundled JBR in recent Android Studio versions is fine.
- If Android Studio reports that SDK Platform 36 is missing, install it via **Tools > SDK Manager**.
- The project keeps the original Kotlin DSL build scripts and Koin dependencies used by the Chapter 5 example.
- I could not run a full Gradle build inside the sandbox because external Gradle/dependency downloads may not be available. The project files have been updated and sanity-checked textually.

## Kotlin compilerOptions DSL fix

Android Studio reported:

`Using 'jvmTarget: String' is an error. Please migrate to the compilerOptions DSL.`

The app module now uses the Kotlin compilerOptions DSL:

```kotlin
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}
```

The old block was removed:

```kotlin
kotlinOptions {
    jvmTarget = "17"
}
```
