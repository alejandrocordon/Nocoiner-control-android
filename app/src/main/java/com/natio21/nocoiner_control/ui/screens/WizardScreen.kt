package com.natio21.nocoiner_control.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.natio21.nocoiner_control.MainViewModel
import com.natio21.nocoiner_control.MinerPrefs

@Composable
fun WizardScreen(
    onWizardComplete: () -> Unit,
    viewModel: MainViewModel,
) {
    // Aquí manejamos un estado local para IP y API Key o lo podemos alojar en el ViewModel
    val uiState = viewModel.appSettingsUiState // data class { ip, apiKey, errorMsg, etc. }

    Column(
        // Centrado en la pantalla
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,

    ) {
        Text(text = "Setup your Miner")

        OutlinedTextField(
            value = uiState.value.ip,
            onValueChange = {
                viewModel.updateIp(it)
                            },
            label = { Text("Miner IP or DNS") },
            placeholder = { Text("Miner IP or DNS") },
        )

        OutlinedTextField(
            value = uiState.value.apiKey,
            onValueChange = {
                viewModel.updateApiKey(it)
                            },
            label = { Text("API Key") },
            placeholder = { Text("API Key") },
        )

        Button(
            onClick = {
                // Llamamos a la lógica del ViewModel:
                // 1. Intentar conectar y validar IP
                // 2. Validar API Key
                // 3. Guardar en SharedPreferences
                // 4. Llamar onWizardComplete()
                viewModel.validateAndSave() { it ->
                    if (it) {
                        viewModel.saveIp(uiState.value.ip)
                        viewModel.saveApiKey(uiState.value.ip)
                        onWizardComplete()
                    } else {
                        // mostrar error en uiState
                    }
                }
            }
        ) {
            Text("Finish Setup")
        }

        // Error message if any
        if (uiState.value.errorMsg != null) {
            Text(uiState.value.errorMsg!!, color = MaterialTheme.colorScheme.error)
        }
    }
}