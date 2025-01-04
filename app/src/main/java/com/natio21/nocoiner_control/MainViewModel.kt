package com.natio21.nocoiner_control

import android.app.Application
import android.content.ActivityNotFoundException
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.natio21.nocoiner_control.openapi.client.models.CoolingSettings
import com.natio21.nocoiner_control.openapi.client.models.MinerSettings
import com.natio21.nocoiner_control.openapi.client.models.ModeSettings
import com.natio21.nocoiner_control.openapi.client.models.SettingsRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

val wizardUiState: WizardUiState = WizardUiState()

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val minerApiService: MinerApiService,
    private val minerPrefs: MinerPrefs,
    @ApplicationContext val context: Context
) : AndroidViewModel(application) {


    private val _basicUiState = MutableStateFlow(BasicUiState())
    private val _advancedUiState = MutableStateFlow(AdvancedUiState())
    private val _appSettingsUiState = MutableStateFlow(AppSettingsUiState())

    val appSettingsUiState: StateFlow<AppSettingsUiState> = _appSettingsUiState
    val basicUiState: StateFlow<BasicUiState> = _basicUiState
    val advancedUiState: StateFlow<AdvancedUiState> = _advancedUiState

    var ip = mutableStateOf("")
        private set

    var apiKey = mutableStateOf("")
        private set


    fun getIp(): String? {
        return minerPrefs.getIp()
    }

    fun getApiKey(): String? {
        return minerPrefs.getApiKey()
    }

    fun updateIp(newIp: String) {
        ip.value = newIp
        minerPrefs.saveIp(newIp)
    }

    fun updateApiKey(newApiKey: String) {
        apiKey.value = newApiKey
        minerPrefs.saveApiKey(newApiKey)
    }

    fun hasSavedData(): Boolean {
        return !minerPrefs.getIp().isNullOrEmpty() && !minerPrefs.getApiKey().isNullOrEmpty()
    }

    fun getSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            _basicUiState.update { it.copy(isLoading = true) }
            try {
                val settingsResponse =
                    minerApiService.getSettings(minerPrefs.getApiKey().toString())
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

    fun setTemperature(newTemp: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _basicUiState.update { it.copy(isLoading = true) }
            try {
                val settingsRequest = SettingsRequest(
                    miner = MinerSettings(
                        cooling = CoolingSettings(
                            mode = ModeSettings(name = "auto", param = newTemp),
                            fan_min_count = 4,
                            fan_min_duty = 10
                        )
                    )
                )
                val settingsResponse = minerApiService.updateSettings(
                    minerPrefs.getApiKey().toString(),
                    settingsRequest
                )
                _basicUiState.update {
                    it.copy(
                        currentTemperature = settingsResponse.miner.cooling.mode.param,
                        isLoading = false,
                        showSuccessMessage = true,
                        errorMsg = null
                    )
                }
            } catch (e: Exception) {
                _basicUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = "Error al actualizar temperatura: ${e.message} ip: ${minerPrefs.getIp()} apiKey: ${minerPrefs.getApiKey()}"
                    )
                }
            }
        }
    }

    fun getHashrate() {
        viewModelScope.launch(Dispatchers.IO) {
            _advancedUiState.update { it.copy(isLoading = true) }
            try {
                val summary = minerApiService.getSummary(minerPrefs.getApiKey().toString())
                val hashrate = summary.miner.average_hashrate
                _advancedUiState.update {
                    it.copy(
                        hashrate = hashrate.toString(),
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _advancedUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = "Error al consultar hashrate: ${e.message} ip: ${minerPrefs.getIp()} apiKey: ${minerPrefs.getApiKey()}"
                    )
                }
            }
        }
    }


    fun loadTemperature() {
        viewModelScope.launch(Dispatchers.IO) {
            _basicUiState.update { it.copy(isLoading = true, errorMsg = null) }


            try {
                val settingsResponse =
                    minerApiService.getSettings(minerPrefs.getApiKey().toString())

                // 3. Actualizar el estado con el nuevo valor
                _basicUiState.update {
                    it.copy(
                        currentTemperature = settingsResponse.miner.cooling.mode.param,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                // Manejar error y volver a notificar que no está cargando
                Log.e(
                    TAG,
                    "Error al consultar temperatura: ${e.message} ip: ${minerPrefs.getIp()} apiKey: ${minerPrefs.getApiKey()}"
                )
                //_basicUiState.update {
                //    it.copy(
                //        isLoading = false,
                //        errorMsg = "Error al consultar temperatura: ${e.message} ip: ${appSettingsUiState.value.ip} apiKey: ${appSettingsUiState.value.apiKey}"
                //    )
                //}
            }
        }
    }


    suspend fun checkConnectivity(): Boolean {
        var isConnected = false
        viewModelScope.launch(Dispatchers.IO) {
            _basicUiState.update { it.copy(isLoading = true, errorMsg = null) }
            try {
                val settingsResponse = minerApiService.getSettings(minerPrefs.getApiKey().toString())
                _basicUiState.update {
                    it.copy(
                        currentTemperature = settingsResponse.miner.cooling.mode.param,
                        isLoading = false
                    )
                }
                _appSettingsUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = null
                    )
                }
                isConnected = true
            } catch (e: Exception) {
                _appSettingsUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = e.message
                    )
                }
                _basicUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = e.message
                    )
                }
            }
        }.join() // Wait for the coroutine to finish
        return isConnected
    }

    fun validateAndSave(onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val isConnected = checkConnectivity()
            onComplete(isConnected)
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
        basicUiState.value.errorMsg = null
    }

    // Advanced
    fun editPool(pool: Pool) { /* ... */
    }

    fun deletePool(pool: Pool) { /* ... */
    }

    fun createNewPool() { /* ... */
        Toast.makeText(context, "Functionality not implemented yet", Toast.LENGTH_SHORT).show()
    }

    fun openMinerWeb() {
        val url = minerPrefs.getIp()
        Log.d("MainViewModel", "URL: $url")
        if (!url.isNullOrEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    "No application can handle this request. Please install a web browser.",
                    Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }
        } else {
            Toast.makeText(context, "Invalid URL", Toast.LENGTH_SHORT).show()
        }
    }

}

// STATES MODELS
data class WizardUiState(

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
    var isLoading: Boolean = false,
    var errorMsg: String? = null
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
