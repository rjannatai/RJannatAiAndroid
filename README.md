# RJannatAi Android Studio Starter

A Kotlin + Jetpack Compose starter app for **RJannatAi — One AI. Infinite Possibilities.**

## Included
- Home dashboard
- Bottom navigation: Home, Chat, Tools, Profile
- AI tool cards: Chat, Image, Video, Voice, Writer, Translate
- Demo chat UI (local placeholder only)
- Dark purple visual theme
- Bengali / English / Hindi product direction

## Open in Android Studio
1. Extract the ZIP.
2. Open Android Studio.
3. Choose **Open** and select the `RJannatAiAndroid` folder.
4. Allow Gradle sync. Use JDK 17.
5. Run on an Android emulator or a physical Android device.

## Important: AI backend is not connected yet
The chat screen is a UI demo. Image, video and voice cards are feature placeholders.
To connect AI services, create a backend (for example, Firebase Functions or your own HTTPS server) and have the Android app call that backend.

**Never put an OpenAI API key or other secret key inside the Android app, source code, APK, or Git repository.** Mobile apps can be reverse engineered. Keep provider keys on the server and let the server authenticate and limit requests.

## Suggested next development steps
1. Firebase Authentication for sign-in.
2. Firestore for user settings and chat metadata.
3. Secure backend endpoint for AI chat.
4. Add image generation, speech input/output and video-provider integrations.
5. Add privacy policy, rate limits, usage limits and error handling.

## Project details
- Application ID: `com.rjannatai.app`
- Minimum Android: API 24 (Android 7.0)
- Compile / target SDK: 35
- Kotlin + Jetpack Compose + Material 3
