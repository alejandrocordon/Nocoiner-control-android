package com.natio21.nocoiner_control.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WizardScreen(
    onWizardComplete: () -> Unit,
    viewModel: MainViewModel
) {
    // Aquí manejamos un estado local para IP y API Key o lo podemos alojar en el ViewModel
    val uiState = viewModel.wizardUiState // data class { ip, apiKey, errorMsg, etc. }

    Column(
        // Diseño que prefieras
    ) {
        Text(text = "Setup your Miner")

        OutlinedTextField(
            value = uiState.ip,
            onValueChange = { viewModel.updateIp(it) },
            label = { Text("Miner IP or DNS") }
        )

        OutlinedTextField(
            value = uiState.apiKey,
            onValueChange = { viewModel.updateApiKey(it) },
            label = { Text("API Key") }
        )

        Button(
            onClick = {
                // Llamamos a la lógica del ViewModel:
                // 1. Intentar conectar y validar IP
                // 2. Validar API Key
                // 3. Guardar en SharedPreferences
                // 4. Llamar onWizardComplete()
                viewModel.validateAndSave {
                    if (it) {
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
        if (uiState.errorMsg != null) {
            Text(uiState.errorMsg, color = MaterialTheme.colorScheme.error)
        }
    }
}