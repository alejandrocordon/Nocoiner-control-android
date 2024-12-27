package com.natio21.nocoiner_control

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    // STATES
    val wizardUiState: WizardUiState = WizardUiState()
    val basicUiState: BasicUiState = BasicUiState()
    val advancedUiState: AdvancedUiState = AdvancedUiState()
    val settingsUiState: SettingsUiState = SettingsUiState()

    // Para Splash
    fun hasSavedData(): Boolean {
        // Leer de SharedPreferences
        //TODO: add this logic
         return false
    }

    // Wizard
    fun updateIp(ip: String) { wizardUiState.ip = ip }
    fun updateApiKey(key: String) { wizardUiState.apiKey = key }

    fun validateAndSave(callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            // Validar IP y API Key
            val isValid = /* tu lógica de validación */
                if (isValid) {
                    // Guardar en SharedPreference
                    callback(true)
                } else {
                    wizardUiState.errorMsg = "Cannot validate IP/API Key"
                    callback(false)
                }
        }
    }

    // Basic
    fun changeTemperature(newTemp: Int) { /* ... */ }
    fun updateTimer(minutes: Int) { /* ... */ }
    fun startTimer(onFinished: () -> Unit) { /* ... */ }

    // Para ocultar diálogos
    fun clearError() {
        basicUiState.errorMsg = null
    }

    // Advanced
    fun editPool(pool: Pool) { /* ... */ }
    fun deletePool(pool: Pool) { /* ... */ }
    fun createNewPool() { /* ... */ }
    fun openMinerWeb(ip: String) { /* ... intent para navegador externo */ }

    // Settings
    fun onSettingsIpChange(value: String) { settingsUiState.ip = value }
    fun onSettingsApiKeyChange(value: String) { settingsUiState.apiKey = value }
    fun onDarkThemeToggled(isDark: Boolean) { settingsUiState.isDarkTheme = isDark }
    fun saveSettings() { /* ... SharedPreferences ... */ }
}

// STATES MODELS
data class WizardUiState(
    var ip: String = "",
    var apiKey: String = "",
    var errorMsg: String? = null
)

data class BasicUiState(
    var currentTemperature: Int = 20,
    var timerMinutes: Int = 0,
    var showSuccessMessage: Boolean = false,
    var errorMsg: String? = null
)

data class AdvancedUiState(
    val minerIp: String = "",
    val pools: List<Pool> = emptyList(),
    val hashrate: String = "", // Ej: “56 MH/s”
    // etc.
)

data class SettingsUiState(
    var ip: String = "",
    var apiKey: String = "",
    var isDarkTheme: Boolean = false
)

data class Pool(
    val url: String,
    val priority: Int = 0,
    val status: String = ""
)
