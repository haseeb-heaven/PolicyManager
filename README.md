# PolicyManager

A modern Android application for managing insurance policies, built with **Kotlin** and **Jetpack Compose**.

## Overview

PolicyManager helps users manage their insurance policies efficiently with an intuitive interface to view policy details, track premium due dates, and monitor policy status.

## How It Works

The app displays insurance policies in expandable cards on a dashboard. Users can tap to view detailed information including policy numbers, status, premium amounts, and due dates. The interface uses color-coded badges to quickly identify active and lapsed policies.

## Features

- **Policy summary dashboard** with all policies at a glance
- **Expandable cards** with detailed policy information
- **Status tracking** with visual indicators (Active/Lapsed)
- **Premium due date tracking**
- **Policy information display** (name, number, dates, amounts)
- **Smooth animations** for enhanced user experience

## How to Run

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/haseeb-heaven/PolicyManager.git
    ```
2.  **Open in Android Studio:**
    * Launch Android Studio.
    * Select "Open an Existing Project".
    * Navigate to the cloned `policymanager` directory and open it.
3.  **Gradle Sync:**
    * Android Studio will automatically start a Gradle sync. Wait for it to complete. This will download all the required dependencies.
4.  **Set up Emulator/Device:**
    * Ensure you have an Android emulator set up with an Android API level of 35 or higher, or a physical Android device connected to your computer with USB debugging enabled.
5.  **Build and Run:**
    * Click the Run button in Android Studio.
    * Select your emulator or connected device as the target.
    * The app will be built, installed, and launched on the selected target.

## Key Technologies

- Kotlin
- Material 3 Design
- MVVM Architecture
- StateFlow
- Navigation Compose