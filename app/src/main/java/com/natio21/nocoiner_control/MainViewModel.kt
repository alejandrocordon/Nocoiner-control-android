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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.natio21.nocoiner_control.openapi.client.models.CoolingSettings
import com.natio21.nocoiner_control.openapi.client.models.MetricEntry
import com.natio21.nocoiner_control.openapi.client.models.MetricsResponse
import com.natio21.nocoiner_control.openapi.client.models.MinerSettingsCooling
import com.natio21.nocoiner_control.openapi.client.models.ModeSettings
import com.natio21.nocoiner_control.openapi.client.models.SettingsRequestCooling
import com.natio21.nocoiner_control.openapi.client.models.SettingsResponse
import com.natio21.nocoiner_control.openapi.client.models.SummaryResponse
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
    private val minerPrefs: MinerPrefs,
    //private val minerApiService: MinerApiService,
    private val repository: ServerRepository,
    @ApplicationContext val context: Context
) : AndroidViewModel(application) {


    private val _basicUiState = MutableStateFlow(BasicUiState())
    private val _advancedUiState = MutableStateFlow(AdvancedUiState())
    private val _appSettingsUiState = MutableStateFlow(AppSettingsUiState())
    private var minerApiService: MinerApiService? = null

    val appSettingsUiState: StateFlow<AppSettingsUiState> = _appSettingsUiState
    val basicUiState: StateFlow<BasicUiState> = _basicUiState
    val advancedUiState: StateFlow<AdvancedUiState> = _advancedUiState

    //Metrics
    private val _metrics = MutableLiveData<List<Pair<Long, Float>>>()
    val metrics: LiveData<List<Pair<Long, Float>>> get() = _metrics

    private val _showEditPoolsDialog = MutableStateFlow(false)
    val showEditPoolsDialog: StateFlow<Boolean> = _showEditPoolsDialog


    //Pools
    private val _poolsData = MutableStateFlow(List(4) { PoolData("", "") })
    val poolsData: StateFlow<List<PoolData>> = _poolsData

    data class PoolData(val url: String, val port: String)





    var ip = mutableStateOf("")
    var apiKey = mutableStateOf("")

    fun init() {
        ip.value = minerPrefs.getIp() ?: ""
        apiKey.value = minerPrefs.getApiKey() ?: ""
        appSettingsUiState.value.ip = minerPrefs.getIp() ?: ""
        appSettingsUiState.value.apiKey = minerPrefs.getApiKey() ?: ""
    }

    fun getIpFromPrefs(): String? {
        return minerPrefs.getIp()
    }

    fun getApiKeyFromPrefs(): String? {
        return minerPrefs.getApiKey()
    }

    fun updateIp(newIp: String) {
        ip.value = newIp
        appSettingsUiState.value.ip = newIp
        minerPrefs.saveIp(newIp)
    }
    /**
     * Llamado cuando el usuario introduce la IP y la validas
     */
    fun setIp(newIp: String) {
        // 1. Validar IP (opcional, con regex).
        // 2. Construir apiService dinámicamente
        ip.value = newIp
        appSettingsUiState.value.ip = newIp
        minerPrefs.saveIp(newIp)
        minerApiService = DynamicApiFactory.create(newIp)

        //// Si quieres, podrías “pingear” automáticamente para comprobar que el servidor responde:
        //checkServer()
    }



    fun toggleEditPoolsDialog(show: Boolean) {
        _showEditPoolsDialog.value = show
    }

    fun updatePoolData(index: Int, url: String, port: String) {
        _poolsData.value = _poolsData.value.toMutableList().apply {
            this[index] = PoolData(url, port)
        }
    }


    fun updateApiKey(newApiKey: String) {
        Log.d("MainViewModel", "apiKey oldApiKey: ${minerPrefs.getApiKey()} newApiKey: $newApiKey")
        apiKey.value = newApiKey
        appSettingsUiState.value.apiKey = newApiKey
        minerPrefs.saveApiKey(newApiKey)
        Log.d("MainViewModel", "apiKey value: ${apiKey.value} ApiKeyPrefs: $${minerPrefs.getApiKey()}")
    }

    fun hasSavedData(): Boolean {
        Log.d("MainViewModel", "hasSavedData: ${ip.value} ${apiKey.value}")
        Log.d("MainViewModel", "hasSavedData: ${appSettingsUiState.value.ip} ${appSettingsUiState.value.apiKey}")
        Log.d("MainViewModel", "hasSavedData: ${minerPrefs.getIp()} ${minerPrefs.getApiKey()}")

        return !minerPrefs.getIp().isNullOrEmpty() && !minerPrefs.getApiKey().isNullOrEmpty()
    }

    fun getSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val settingsResponse =
                    minerApiService?.getSettings(minerPrefs.getApiKey().toString())
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
            }
        }
    }

    fun setTemperature(newTemp: Int) {
        Log.d("MainViewModel", "setTemperature: ${ip.value} ${apiKey.value}")
        Log.d("MainViewModel", "setTemperature: ${appSettingsUiState.value.ip} ${appSettingsUiState.value.apiKey}")
        Log.d("MainViewModel", "setTemperature: ${minerPrefs.getIp()} ${minerPrefs.getApiKey()}")


        viewModelScope.launch(Dispatchers.IO) {
            _basicUiState.update { it.copy(isLoading = true) }
            try {
                val settingsRequest = SettingsRequestCooling(
                    miner = MinerSettingsCooling(
                        cooling = CoolingSettings(
                            mode = ModeSettings(name = "auto", param = newTemp),
                            fan_min_count = 4,
                            fan_min_duty = 10,
                            fan_max_duty = 100
                        )
                    )
                )
                val settingsResponse = minerApiService?.updateSettingsCooling(
                    minerPrefs.getApiKey().toString(),
                    settingsRequest
                )
                _basicUiState.update {
                    it.copy(
                        currentTemperature = settingsResponse!!.miner.cooling.mode.param,
                        isLoading = false,
                        showSuccessMessage = true,
                        errorMsg = null
                    )
                }
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                _basicUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = "Error al actualizar temperatura: ${e.message} ip: ${minerPrefs.getIp()} apiKey: ${minerPrefs.getApiKey()}"
                    )
                }
            }
        }
    }

    fun pauseMinnig() {
        Log.d("MainViewModel", "setToSleep: ${ip.value} ${apiKey.value}")

        viewModelScope.launch(Dispatchers.IO) {
            _basicUiState.update { it.copy(isLoading = true) }
            try {
                val settingsResponse = minerApiService?.pauseMining(
                    minerPrefs.getApiKey().toString(),
                )
                Log.d("MainViewModel", "setToSleep: $settingsResponse")
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                _basicUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = "Error al poner a sleep: ${e.message} ip: ${minerPrefs.getIp()} apiKey: ${minerPrefs.getApiKey()}"
                    )
                }
            }
        }
    }

    fun resumeMinnig() {
        Log.d("MainViewModel", "setToSleep: ${ip.value} ${apiKey.value}")

        viewModelScope.launch(Dispatchers.IO) {
            _basicUiState.update { it.copy(isLoading = true) }
            try {
                val settingsResponse = minerApiService?.resumeMining(
                    minerPrefs.getApiKey().toString(),
                )
                Log.d("MainViewModel", "setToSleep: $settingsResponse")
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                _basicUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = "Error al poner a sleep: ${e.message} ip: ${minerPrefs.getIp()} apiKey: ${minerPrefs.getApiKey()}"
                    )
                }
            }
        }
    }

    fun getSummaryAndSettings() {
        Log.d("MainViewModel", "getSummary: ${ip.value} ${apiKey.value}")
        Log.d("MainViewModel", "getSummary: ${appSettingsUiState.value.ip} ${appSettingsUiState.value.apiKey}")
        Log.d("MainViewModel", "getSummary: ${minerPrefs.getIp()} ${minerPrefs.getApiKey()}")

        viewModelScope.launch(Dispatchers.IO) {
            _advancedUiState.update { it.copy(isLoading = true) }
            try {
                val summary = minerApiService?.getSummary(minerPrefs.getApiKey().toString())
                val settings = minerApiService?.getSettings(minerPrefs.getApiKey().toString())
                _advancedUiState.update {
                    it.copy(
                        summary = summary,
                        settings = settings,
                        isLoading = false,
                    )
                }
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                _advancedUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = "Error al consultar hashrate: ${e.message} ip: ${minerPrefs.getIp()} apiKey: ${minerPrefs.getApiKey()}"
                    )
                }
            }
        }
    }

    fun getHashrateHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            _advancedUiState.update { it.copy(isLoading = true) }
            //try {
            //    val hashrateHistory = minerApiService?.getMinerInfo().miner.getHashrateHistory(minerPrefs.getApiKey().toString())
            //    _advancedUiState.update {
            //        it.copy(
            //            hashrateHistory = hashrateHistory,
            //            isLoading = false
            //        )
            //    }
            //} catch (e: Exception) {
            //    FirebaseCrashlytics.getInstance().recordException(e)
            //    _advancedUiState.update {
            //        it.copy(
            //            isLoading = false,
            //            errorMsg = "Error al consultar el histórico de hashrate: ${e.message} ip: ${minerPrefs.getIp()} apiKey: ${minerPrefs.getApiKey()}"
            //        )
            //    }
            //}
        }
    }

    fun getInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _basicUiState.update { it.copy(isLoading = true) }
            try {
                val minerInfo = minerApiService?.getMinerInfo()
                Log.d("MainViewModel", "MinerInfo: $minerInfo")
                _basicUiState.update {
                    it.copy(
                        minerInfo = minerInfo,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                Log.e(
                    TAG,
                    "Error al consultar info: ${e.message} ip: ${minerPrefs.getIp()}}"
                )
                _advancedUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = "Error al consultar hashrate: ${e.message} ip: ${minerPrefs.getIp()} apiKey: ${minerPrefs.getApiKey()}"
                    )
                }
            }
        }
    }



    fun getMetrics1_15() {
        Log.d("MainViewModel", "getMetrics1_15: ${ip.value} ${apiKey.value}")
        Log.d("MainViewModel", "getMetrics1_15: ${appSettingsUiState.value.ip} ${appSettingsUiState.value.apiKey}")
        Log.d("MainViewModel", "getMetrics1_15: ${minerPrefs.getIp()} ${minerPrefs.getApiKey()}")

        viewModelScope.launch(Dispatchers.IO) {
            _basicUiState.update { it.copy(isLoading = true) }
            try {
                val metrics = minerApiService?.getMetrics1_15()
                Log.d("MainViewModel", "Metrics: $metrics")
                _basicUiState.update {
                    it.copy(
                        isLoading = false,
                        metrics = metrics?.metrics
                    )
                }
            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                _basicUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = "Error al consultar hashrate: ${e.message} ip: ${minerPrefs.getIp()} apiKey: ${minerPrefs.getApiKey()}"
                    )
                }
            }
        }
    }



    fun isValidIpOrDomain(ip: String): Boolean {
        val ipPattern = Regex("^(http://|https://)?(([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}|(\\d{1,3}\\.){3}\\d{1,3})$")
        return ipPattern.matches(ip)
    }

    private suspend fun checkConnectivity(): Boolean {
        var isConnected = false
        viewModelScope.launch(Dispatchers.IO) {
            _basicUiState.update { it.copy(isLoading = true, errorMsg = null) }
            try {
                val settingsResponse = minerApiService?.getSettings(minerPrefs.getApiKey().toString())
                _basicUiState.update {
                    it.copy(
                        currentTemperature = settingsResponse!!.miner.cooling.mode.param,
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
                FirebaseCrashlytics.getInstance().recordException(e)
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

    fun getAppVersion(): String {
        return context.packageManager.getPackageInfo(context.packageName, 0).versionName.toString()
    }

    fun checkConnectivityAndSave(onComplete: (Boolean) -> Unit) {
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
    fun editPool(pool: PoolSummary) { /* ... */
    }

    fun deletePool(pool: PoolSummary) { /* ... */
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
                FirebaseCrashlytics.getInstance().recordException(e)
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
    var isLoading: Boolean = false,
    var minerInfo: MinerInfo? = null,
    var metrics: List<MetricEntry>? = null,
)

data class AdvancedUiState(
    val minerIp: String = "",
    val hashrate: String = "", // Ej: “56 MH/s”
    var isLoading: Boolean = false,
    var errorMsg: String? = null,
    var summary: SummaryResponse? = null,
    var settings: SettingsResponse? = null
)

data class AppSettingsUiState(
    var ip: String = "",
    var apiKey: String = "",
    var isDarkTheme: Boolean = false,
    var isLoading: Boolean = false,
    var errorMsg: String? = null
)

data class PoolSummary(
    val url: String,
    val priority: Int = 0,
    val status: String = "",
    var isLoading: Boolean = false
)
