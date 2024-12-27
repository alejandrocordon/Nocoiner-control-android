package com.natio21.nocoiner_control

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {


    // STATES
    val wizardUiState: WizardUiState = WizardUiState()
    val basicUiState: BasicUiState = BasicUiState()
    val advancedUiState: AdvancedUiState = AdvancedUiState()
    val settingsUiState: SettingsUiState = SettingsUiState()

    fun hasSavedData(): Boolean {
        val context = getApplication<Application>().applicationContext
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("nocoiner_prefs", Context.MODE_PRIVATE)
        val ip = sharedPreferences.getString("ip_address", null)
        val apiKey = sharedPreferences.getString("api_key", null)
        return !ip.isNullOrEmpty() && !apiKey.isNullOrEmpty()
    }

    fun getIpAndApiKey(): Pair<String, String> {
        val context = getApplication<Application>().applicationContext
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("nocoiner_prefs", Context.MODE_PRIVATE)
        val ip = sharedPreferences.getString("ip_address", null)
        val apiKey = sharedPreferences.getString("api_key", null)
        return Pair(ip!!, apiKey!!)
    }

    // Wizard
    fun updateIp(ip: String) {
        wizardUiState.ip = ip
    }

    fun updateApiKey(key: String) {
        wizardUiState.apiKey = key
    }

    fun validateAndSave(callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val context = getApplication<Application>().applicationContext
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("nocoiner_prefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("ip_address", wizardUiState.ip)
            editor.putString("api_key", wizardUiState.apiKey)
            editor.apply()


            val isValid = hasSavedData()
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
    fun changeTemperature(newTemp: Int) { /* ... */
    }

    fun updateTimer(minutes: Int) { /* ... */
    }

    fun startTimer(onFinished: () -> Unit) { /* ... */
    }

    // Para ocultar diálogos
    fun clearError() {
        basicUiState.errorMsg = null
    }

    // Advanced
    fun editPool(pool: Pool) { /* ... */
    }

    fun deletePool(pool: Pool) { /* ... */
    }

    fun createNewPool() { /* ... */
    }

    fun openMinerWeb(ip: String) {
        val context = getApplication<Application>().applicationContext
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ip)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    // Settings
    fun onSettingsIpChange(value: String) {
        settingsUiState.ip = value
    }

    fun onSettingsApiKeyChange(value: String) {
        settingsUiState.apiKey = value
    }

    fun onDarkThemeToggled(isDark: Boolean) {
        settingsUiState.isDarkTheme = isDark
    }

    fun saveSettings() { /* ... SharedPreferences ... */
    }
}

// STATES MODELS
data class WizardUiState(
    var ip: String = "192.168.1.121",
    var apiKey: String = "asdfasdfasdfasdfasdfasdfasdfabtc",
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
