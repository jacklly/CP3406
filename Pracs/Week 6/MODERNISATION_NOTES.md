# Chapter Seven modernisation notes

Updated for the Week 6 navigation demo.

## Build updates

- Updated Android Gradle Plugin to `8.13.2`.
- Updated the Gradle wrapper distribution URL to `gradle-8.13-bin.zip`.
- Updated Kotlin plugins to `2.0.21`.
- Added the Kotlin Compose compiler plugin: `org.jetbrains.kotlin.plugin.compose`.
- Removed the old explicit `composeOptions.kotlinCompilerExtensionVersion` setting.
- Updated Java/Kotlin compilation to JVM 17.
- Replaced the deprecated `packagingOptions` block with the modern `packaging` block.
- Kept repositories centralised in `settings.gradle.kts` using `google()`, `mavenCentral()`, and `gradlePluginPortal()` for plugins.
- Updated AndroidX, Compose BOM, Navigation, Window, Retrofit, coroutines, serialization, OkHttp, Coil, Koin, and test dependency versions in `gradle/libs.versions.toml`.
- Added `material-icons-extended` because the demo uses Material icon vectors.

## Source updates

- Encoded the serialised `Cat` navigation argument with `Uri.encode(...)` before navigation.
- Decoded the argument with `Uri.decode(...)` before deserialising it.

This avoids route parsing problems when JSON contains characters that are meaningful in a navigation route.

## Verification performed

- Parsed `gradle/libs.versions.toml` successfully.
- Checked for old build configuration strings such as `jcenter`, `kotlinOptions`, `composeOptions`, `packagingOptions`, AGP `8.2.1`, Kotlin `1.8.20`, and the old Compose BOM.
- Attempted to run `./gradlew --version`, but the sandbox could not resolve `services.gradle.org`, so a full Gradle wrapper download/build could not be performed here.

## Local verification to run

From the project root, run:

```bash
./gradlew --version
./gradlew clean assembleDebug
```

On Windows PowerShell, run:

```powershell
.\gradlew.bat --version
.\gradlew.bat clean assembleDebug
```
