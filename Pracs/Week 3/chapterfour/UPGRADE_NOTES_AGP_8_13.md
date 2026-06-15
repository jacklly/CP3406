# Upgrade notes: Android Studio / AGP 8.13.x

This project has been updated from the older Android Gradle Plugin / Kotlin / Compose setup to a configuration suitable for Android Studio using AGP 8.13.x.

## Main changes

- Updated Android Gradle Plugin from `8.2.1` to `8.13.2`.
- Updated Gradle wrapper distribution from `8.2` to `8.13`.
- Updated Kotlin Gradle plugin from `1.8.20` to `2.3.21`.
- Added the Kotlin Compose Compiler Gradle plugin:
  - `org.jetbrains.kotlin.plugin.compose` version `2.3.21`
- Removed the old Compose compiler extension setting:
  - `composeOptions { kotlinCompilerExtensionVersion '1.4.6' }`
- Updated compile/target SDK to `36`.
- Updated Java/Kotlin bytecode target from `1.8` to `17`.
- Updated Compose BOM to `2026.05.00`.
- Added `androidx.compose.material:material-icons-extended`, required by the rounded Material icons used in the source code.
- Replaced the older `packagingOptions` block with the current `packaging` DSL.

## Notes

- Android Studio should use JDK 17 or newer for this project. The bundled JBR in recent Android Studio versions is fine.
- If Android Studio reports that SDK Platform 36 is missing, install it via **Tools > SDK Manager**.
- I could not run a full Gradle build inside the sandbox because the Gradle distribution download was blocked by DNS/network access. The project files have been updated based on the current Android/Gradle/Kotlin/Compose configuration guidance.
