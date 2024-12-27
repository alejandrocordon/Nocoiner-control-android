package com.natio21.nocoiner_control.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.natio21.nocoiner_control.MainViewModel

@Composable
fun BasicHeaterScreen(
    viewModel: MainViewModel,
) {
    //val uiState = viewModel.basicUiState
    val uiState by viewModel.basicUiState.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.loadTemperature()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            Text("Current temperature: ${uiState.currentTemperature} °C")
        }
        Button(onClick = { viewModel.loadTemperature() }) {
            Text("Refresh Temperature")
        }

        uiState.errorMsg?.let { error ->
            AlertDialog(
                onDismissRequest = {
                    Log.d("BasicHeaterScreen", "Dismissing error dialog")
                    viewModel.clearError()

                },
                title = { Text("Error") },
                text = { Text(error) },
                confirmButton = {
                    TextButton(onClick = {
                        // podrías limpiar el error en el ViewModel
                        // _basicUiState.update { it.copy(errorMsg = null) }
                    }) {
                        Text("OK")
                    }
                }
            )
        }
        Text(
            text = "Current temperature: ${uiState.currentTemperature} °C",
            style = MaterialTheme.typography.titleLarge
        )

        // Control: botones +/- (y/o slider)
        Row {
            IconButton(onClick = {
                // Lógica para bajar
                viewModel.changeTemperature(uiState.currentTemperature - 1)
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Decrease")
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
            text = { Text(uiState.errorMsg!!) },
            confirmButton = {
                TextButton(onClick = { viewModel.clearError() }) {
                    Text("OK")
                }
            }
        )
    }
}
