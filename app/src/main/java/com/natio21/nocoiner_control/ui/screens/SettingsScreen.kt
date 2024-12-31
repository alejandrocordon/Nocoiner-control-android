package com.natio21.nocoiner_control.ui.screens;

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.natio21.nocoiner_control.MainViewModel

@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    val settingsState = viewModel.appSettingsUiState
    val isDarkTheme = settingsState.value.isDarkTheme

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Settings", style = MaterialTheme.typography.titleLarge)

        // Mostrar IP y API Key
        settingsState.value.ip.let {
            OutlinedTextField(
                value = it,
                onValueChange = { },
                label = { Text("Miner IP/DNS") }
            )
        }
        settingsState.value.apiKey.let {
            OutlinedTextField(
                value = it,
                onValueChange = {  },
                label = { Text("API Key") }
            )
        }

        // Tema: Switch
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dark Mode")
            if (isDarkTheme != null) {
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = {  }
                )
            }
        }

        Button(
            onClick = {
                viewModel.saveSettings()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Guardar")
        }
    }
}