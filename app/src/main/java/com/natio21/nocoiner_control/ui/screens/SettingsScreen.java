package com.natio21.nocoiner_control.ui.screens;

import androidx.compose.runtime.Composable;

@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    val settingsState = viewModel.settingsUiState
    val isDarkTheme = settingsState.isDarkTheme

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Settings", style = MaterialTheme.typography.titleLarge)

        // Mostrar IP y API Key
        OutlinedTextField(
                value = settingsState.ip,
                onValueChange = { viewModel.onSettingsIpChange(it) },
                label = { Text("Miner IP/DNS") }
        )
        OutlinedTextField(
                value = settingsState.apiKey,
                onValueChange = { viewModel.onSettingsApiKeyChange(it) },
                label = { Text("API Key") }
        )

        // Tema: Switch
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dark Mode")
            Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { viewModel.onDarkThemeToggled(it) }
            )
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