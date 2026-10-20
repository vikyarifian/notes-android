# notes-android

Offline-first Android application designed for field officers and warehouse personnel in Cikarang to quickly capture site inspection observations, daily task reminders, and lapangan status updates.

Built for internal deployment across corporate mobile devices via MDM or manual APK sideloading.

## Features

- **Field Inspection Notes:** Capture on-site observations with custom priority tags (`darurat`, `biasa`, `rutin`).
- **Search & Filter:** Real-time search by keyword or category tag.
- **Local Auto-save Drafts:** Preserves draft notes directly to SQLite on Android lifecycle changes (`onPause`/`onStop`) to prevent data loss during app switches.
- **WhatsApp Export:** Export selected inspection logs into pre-formatted plain text snippets for quick pasting into WhatsApp field reporting groups.

## Stack

- **Language:** Kotlin 2.0
- **UI:** Jetpack Compose 1.5
- **Storage:** Local SQLite (Direct SQLiteOpenHelper)
- **Minimum SDK:** Android 8.0 (API Level 26)

## Setup & Build

### Prerequisites
- Android Studio Ladybug or newer
- JDK 17 / JDK 21
- Android SDK 34

### Building the APK

Clone the repository and build the release APK using the Gradle wrapper:

```bash
./gradlew assembleRelease
```

The compiled APK will be generated at:
`app/build/outputs/apk/release/app-release.apk`

### App Screenshot

![Notes Android Screenshot Placeholder](docs/screenshots/app-main.png)

## Deployment Process

1. Generate release build using `./gradlew assembleRelease`.
2. Upload the `.apk` file to the internal IT portal (**Sistem Informasi Internal PT**).
3. Field supervisors can pull the binary or distribute via USB sideload / MDM to field devices in Cikarang branch.

## Known Issues / Framework Workarounds

- Jetpack Compose 1.5 `LazyColumn` key recomposition bug workaround: explicitly keys items by `id` string combined with timestamp to prevent focus loss during rapid local updates.

## License

Internal Corporate Software - Property of PT Internal IT. All rights reserved.