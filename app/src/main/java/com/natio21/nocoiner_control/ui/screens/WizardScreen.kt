package com.natio21.nocoiner_control.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.natio21.nocoiner_control.MainViewModel

@Composable
fun WizardScreen(
    onWizardComplete: () -> Unit,
    viewModel: MainViewModel,
) {
    val ip by viewModel.ip
    val apiKey by viewModel.apiKey

    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
        Text(text = "Setup your Miner")

        OutlinedTextField(
            value = viewModel.getIp() ?: "",
            onValueChange = { viewModel.updateIp(it) },
            label = { Text("Miner IP or DNS") },
            placeholder = { Text("Miner IP or DNS") },
        )

        OutlinedTextField(
            value = viewModel.getApiKey()?: "",
            onValueChange = { viewModel.updateApiKey(it) },
            label = { Text("API Key") },
            placeholder = { Text("API Key") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.validateAndSave { isValid ->
                    if (isValid) {
                        viewModel.updateIp(ip)
                        viewModel.updateApiKey(apiKey)
                        onWizardComplete()
                        Log.d("SettingsScreen", "IP: $ip and API Key: $apiKey saved")
                    } else {
                        Log.e("SettingsScreen", "Error saving IP and API Key")
                    }
                }
            }
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}