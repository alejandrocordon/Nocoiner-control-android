package com.natio21.nocoiner_control.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BasicHeaterScreen(viewModel: MainViewModel) {
    val uiState = viewModel.basicUiState
    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Current temperature: ${uiState.currentTemperature} °C", style = MaterialTheme.typography.titleLarge)

        // Control: botones +/- (y/o slider)
        Row {
            IconButton(onClick = {
                // Lógica para bajar
                viewModel.changeTemperature(uiState.currentTemperature - 1)
            }) {
                Icon(Icons.Default.Remove, contentDescription = "Decrease")
            }
            IconButton(onClick = {
                // Lógica para subir
                viewModel.changeTemperature(uiState.currentTemperature + 1)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Increase")
            }
        }

        // Temporizador
        // Podrías usar un TimePicker o un NumberPicker, aquí un demo con un OutlinedTextField
        OutlinedTextField(
            value = uiState.timerMinutes.toString(),
            onValueChange = { newValue ->
                // Manejo de conversion a Int
                viewModel.updateTimer(newValue.toIntOrNull() ?: 0)
            },
            label = { Text("Timer (minutes)") }
        )

        Button(
            onClick = {
                viewModel.startTimer {
                    // al finalizar, puedes mostrar un snackbar o algo
                }
            }
        ) {
            Text("Start Timer")
        }
    }

    // Manejo de snackbars
    if (uiState.showSuccessMessage) {
        // Ejemplo con SnackbarHost
        SnackbarHost(
            hostState = remember { SnackbarHostState() },
            snackbar = {
                Snackbar {
                    Text("Temperature Updated!")
                }
            }
        )
    }

    // Manejo de diálogos de error
    if (uiState.errorMsg != null) {
        AlertDialog(
            onDismissRequest = { viewModel.clearError() },
            title = { Text("Error") },
            text = { Text(uiState.errorMsg) },
            confirmButton = {
                TextButton(onClick = { viewModel.clearError() }) {
                    Text("OK")
                }
            }
        )
    }
}
