# Nocoiner Control App



<img src="/images/nocoinerphoto.png" alt="drawing" width="350" align="center"/>



# Nocoiner Control App


**Nocoiner Control App** is an Android application designed to manage and monitor a “Nocoiner” miner device over the local network. It provides a user-friendly interface for configuring network settings, adjusting temperature controls, viewing mining pools, and more.

---

## Key Features
- **Setup Wizard**  
  A guided wizard screen for first-time configuration (added in **v0.2.0**) to help users quickly set their miner IP and API Key.
  
- **Basic Mode**  
  Allows you to monitor the current temperature of the miner and adjust setpoints (maximum, average, minimum). Future versions will include an option to “sleep” the miner from this screen.

- **Advanced Mode**  
  Provides access to pool management—adding new pools or navigating to the miner’s built-in web interface. Future plans include regular hashrate updates, multi-miner support, and more.

- **Settings**  
  Lets you store and update the local IP and API Key for the miner. A “Save” action is now confirmed by a Toast message (planned feature) to assure users the config was successful.

---

## Requirements
- **Android Studio** (latest version recommended)  
- **Minimum SDK**: 21 (Lollipop)  
- **Kotlin** and **Jetpack Compose** for UI  
- **Hilt** for dependency injection  
- **OkHttp/Retrofit** (or similar) to connect to the miner’s API (details may vary)

---

## Installation & Setup
1. **Clone or download** this repository.  
2. **Open** it in Android Studio.  
3. **Sync** Gradle, ensuring you have all required plugins (Hilt, Compose, etc.).  
4. **Run** the app on an emulator or physical device connected to the same network as the miner.  
5. **On first launch**, the Wizard will prompt for the local IP and API Key. If these are valid, the Basic and Advanced screens will become available.

---

## Release Notes
### Version 0.2.0
- **New:** Setup wizard screen for initial configuration.  
- **Improvements:** UI alignment and spacing; enhanced state management in `MainViewModel`.  
- **Bug Fixes:**  
  - Fixed IME issues preventing text input.  
  - Resolved `ActivityNotFoundException` when opening a URL.  
- **Technical Changes:** Updated dependencies and Gradle configuration for better performance.  
- **Known Issues:** Certain features (like creating new pools) are not fully implemented and will show a Snackbar placeholder.

*(For more details, see the [Release Notes for 0.2.0](#) section or the [issues page](#) in this repository.)*

---

## Roadmap
- **Multi-Miner Support:** Manage multiple Nocoiners within the same app.  
- **Sleep/Power Control:** A “Sleep Miner” button for quick power down.  
- **Auto-Refresh Hashrate:** Display and periodically update miner hashrate in the Advanced screen.  
- **Scheduling:** Potential to schedule times for hashing or sleep.  
- **Further UI Enhancements:** Refining layouts and theming consistency.

---

## Contributing
We welcome feedback, pull requests, and issue reports. If you have suggestions or want to propose a new feature, please create a GitHub issue or open a pull request.

---

## License
Licensed under [MIT License](LICENSE).  

---
**Thank you for using Nocoiner Control App!**  
Your feedback is greatly appreciated and helps guide future development.