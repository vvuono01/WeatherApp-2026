# WeatherApp-2026 - developed by Vince Vuono

A small demo application that fetches a 7-day weather forecast for the user's current location, and displays a basic overview of the weather for each day, including the weather condition and high and low temperatures.

## Technical Details

### Libraries Used
- Jetpack Compose
- Hilt (for dependency injection)
- RxJava3 (for handling asynchronous data)
- Retrofit (for network connections)
- Room (for local storage via databases)
- OpenMeteo Weather API (for fetching weather data)

### Architectural Decisions
- The app was developed using MVVM principles, creating a dedicated model layer consisting of several repositories (contained in the `data` package, with implementation-specific classes in the `api` package), as well as a ViewModel layer (in the `viewmodel` package) and View layer (in the `ui` package).
- The repositories were set up with interfaces and dependency injection, intending to make it easy to swap specific implementations in and out as needed. For example, the `ForecastRepository` was set up as a generic interface, with an OpenMeteo-specific implementation. None of the OpenMeteo APIs or data structures exist outside of the API package, making it simple to create a new repository with a different API, if desired
- The code was structured as much as possible to assume that the concept of a "Forecast" was just one potential feature of the more broad category of "Weather" - so the app as a whole is a "Weather" app, but the model, view model, and view implementations are "Forecast"-specific; in theory, one could develop other features related to Weather but unrelated to Forecasts using this structure.

### Known Issues
- If the API doesn't return a successful response when the app doesn't have a forecast stored for the current location (for example, when the app is run for the first time), no error state is shown; the app will remain in the loading state indefinitely
- There is currently no way to refresh the API aside from closing the app entirely and reopening it

### Future Improvements
- Implementing dedicated modules for specific APIs, and setting up components for those modules to be included in the app (so there's no danger of cross-contaimination with implementation-specific code in the generic layers, or vice versa)
- Implementing some means of refreshing the API request - likely a pull-to-refresh mechanism
- Implementing more forecast details to show per day
- Improving the UI - in particular, implementing images or at least different colors depending on the overall conditions
- Allowing the user to change display preferences - in particular, allowing them to customize what units they use

## Building and Running
Before you begin, ensure you have the following installed on your local machine:
* JDK 17 or JDK 21
* Android SDK 37 and associated build tools
* (Optional) - A version of Android Studio; the project was developed using **Android Studio Rabbit 2 | 2026.2.2 Patch 2**, but older versions should work (Ladybug or newer is recommended)

### Recommended method - Using Android Studio
- Open the project in Android Studio and either connect a physical device (with USB Debugging enabled in Developer Options) via USB, or start a virtual device from the Device Manager.
- With your desired device selected in the target device dropdown in the top toolbar, click the green Run button

### Alternative method - Using the Command Line (Gradle Wrapper)
If you prefer building directly from the terminal without launching the full IDE, you can use the included Gradle wrapper.

#### Set Environment Variables
Ensure your system knows where the Android SDK is located by exporting the `ANDROID_HOME` variable:

*   **macOS / Linux:**
    ```bash
    export ANDROID_HOME=\$HOME/Library/Android/sdk
    ```
*   **Windows (PowerShell):**
    ```powershell
    \$env:ANDROID_HOME="C:\Users\YOUR_USERNAME\AppData\Local\Android\Sdk"
    ```

#### Build the APK
Navigate to the project root directory and run the compile command:

*   **macOS / Linux:**
    ```bash
    ./gradlew assembleDebug
    ```
*   **Windows:**
    ```cmd
    gradlew.bat assembleDebug
    ```
*   *Note: The generated, un-signed debug APK will be located in `app/build/outputs/apk/debug/`.*

#### Install and Run via CLI
If you have an active device or emulator connected to your computer, you can compile, deploy, and launch the app in one command:

*   **macOS / Linux:**
    ```bash
    ./gradlew installDebug
    ```
*   **Windows:**
    ```cmd
    gradlew.bat installDebug
    ```
