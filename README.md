# MobilityLens

Individual Coding Assignment I - Mobility Lens

## Overview

MobilityLens is a single-screen Android application built with Kotlin and Jetpack Compose. The app helps a developer consider the design implications of the six dimensions that distinguish mobile applications from stationary applications:

1. Input and interaction
2. Screen size, orientation, and density
3. Lifecycle and resource constraints
4. Context awareness
5. Usage patterns
6. Security and privacy expectations

## Purpose

This project demonstrates the ability to:

- Configure the Android development environment
- Create and run a Kotlin/Jetpack Compose application
- Navigate an Android project structure
- Work with Compose state
- Explain the roles of the manifest and Gradle configuration

## Features

- Displays an app title and brief introduction
- Presents one mobility dimension at a time, with a name, description, and a practical developer implication
- Previous and Next controls to move through all six dimensions
- An `OutlinedTextField` for entering an app or feature name
- A validation button that checks for blank input and displays a combined message on success
- Compose state driving all UI changes
- A customized Material theme and typography
- An operational `@Preview` for the main composable

## Tech Stack

- Kotlin
- Jetpack Compose
- Android Studio (Quail 4 Feature Drop)

## Project Structure

```
app/
 └── src/
      └── main/
           ├── java/.../MainActivity.kt
           ├── java/.../Dimensions.kt
           ├── java/.../MobilityScreenLens.kt
           ├── java/.../ui/theme/
           │    ├── Color.kt
           │    ├── Theme.kt
           │    └── Type.kt
           ├── res/values/strings.xml
           └── AndroidManifest.xml
```

## Documentation

See `MobilityLens_Assignment.pdf` for the full written report, including:

- SDK configuration (minSdk, targetSdk, compileSdk)
- The role of MainActivity, AndroidManifest.xml, the app-level Gradle file, the version catalog, and strings.xml
- Rotation observation and analysis
- Mobile-design decision
- Generative-AI and collaboration disclosures

## Running the App

1. Open the project in Android Studio (Quail 4 Feature Drop or later).
2. Let Gradle sync.
3. Run on an emulator or physical device.
