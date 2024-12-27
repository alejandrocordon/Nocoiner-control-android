package com.natio21.nocoiner_control

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// STATES
val wizardUiState: WizardUiState = WizardUiState()

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val minerApiService: MinerApiService,
    private val minerPrefs: MinerPrefs,
    @ApplicationContext val context: Context
) : AndroidViewModel(application) {


    // Ejemplo de un estado de UI para el modo básico
    private val _basicUiState = MutableStateFlow(BasicUiState())
    val appSettingsUiState = AppSettingsUiState()

    //val basicUiState: BasicUiState = BasicUiState()
    val advancedUiState: AdvancedUiState = AdvancedUiState()
    val basicUiState: StateFlow<BasicUiState> = _basicUiState


    fun hasSavedData(): Boolean {
        return !minerPrefs.getIp().isNullOrEmpty() && !minerPrefs.getApiKey().isNullOrEmpty()
    }

    fun getIp(): String {
        return minerPrefs.getIp().toString()
    }

    fun getApiKey(): String {
        return minerPrefs.getApiKey().toString()
    }

    fun getSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            _basicUiState.update { it.copy(isLoading = true) }
            try {
                val settingsResponse = minerApiService.getSettings(getApiKey())
                //_settingsResponse.postValue(settingsResponse)
                _basicUiState.update {
                    it.copy(
                        currentTemperature = settingsResponse.miner.cooling.mode.param,
                        isLoading = false,
                        showSuccessMessage = true,
                        errorMsg = null,
                        timerMinutes = 0
                    )
                }
            } catch (e: Exception) {
                //_settingsResponse.postValue(null)
                _basicUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = "Error al consultar settings: ${e.message}"
                    )
                }
            }
        }
    }

    fun loadTemperature() {
        viewModelScope.launch(Dispatchers.IO) {
            // 1. Indicar que estamos cargando
            _basicUiState.update { it.copy(isLoading = true, errorMsg = null) }


            // 2. Hacer la llamada “lenta” (red, disco, etc.)
            try {
                val settingsResponse = minerApiService.getSettings(getApiKey())
                //_appSettingsResponse.postValue(settingsResponse)

                // 3. Actualizar el estado con el nuevo valor
                _basicUiState.update {
                    it.copy(currentTemperature = settingsResponse.miner.cooling.mode.param,
                        isLoading = false)
                }
            } catch (e: Exception) {
                // Manejar error y volver a notificar que no está cargando
                _basicUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = "Error al consultar temperatura: ${e.message}"
                    )
                }
            }
        }
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
            minerPrefs.saveIp(wizardUiState.ip)
            minerPrefs.saveApiKey(wizardUiState.apiKey)

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
    fun changeTemperature(newTemp: Int) {
        _basicUiState.update { currentState ->
            currentState.copy(currentTemperature = newTemp)
        }
    }

    fun updateTimer(minutes: Int) { /* ... */
    }

    fun startTimer(onFinished: () -> Unit) { /* ... */
    }

    // Para ocultar diálogos
    fun clearError() {
        basicUiState.value?.errorMsg = null
    }

    // Advanced
    fun editPool(pool: Pool) { /* ... */
    }

    fun deletePool(pool: Pool) { /* ... */
    }

    fun createNewPool() { /* ... */
    }

    fun openMinerWeb(ip: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ip)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    // Settings
    fun onSettingsIpChange(value: String) {
        appSettingsUiState.ip = value
    }

    fun onSettingsApiKeyChange(value: String) {
        appSettingsUiState.apiKey = value
    }

    fun onDarkThemeToggled(isDark: Boolean) {
        appSettingsUiState.isDarkTheme = isDark
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
    var errorMsg: String? = null,
    var isLoading: Boolean = false
)

data class AdvancedUiState(
    val minerIp: String = "",
    val pools: List<Pool> = emptyList(),
    val hashrate: String = "", // Ej: “56 MH/s”
    var isLoading: Boolean = false
)

data class AppSettingsUiState(
    var ip: String = "",
    var apiKey: String = "",
    var isDarkTheme: Boolean = false,
    var isLoading: Boolean = false,
    var errorMsg: String? = null
)

data class Pool(
    val url: String,
    val priority: Int = 0,
    val status: String = "",
    var isLoading: Boolean = false
)
