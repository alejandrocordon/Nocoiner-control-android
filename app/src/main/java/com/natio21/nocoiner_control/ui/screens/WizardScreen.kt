package com.natio21.nocoiner_control.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.natio21.nocoiner_control.DynamicApiFactory
import com.natio21.nocoiner_control.MainViewModel
import com.natio21.nocoiner_control.R
import com.natio21.nocoiner_control.ui.theme.NatioOrange40

@Composable
fun WizardScreen(
    onWizardComplete: () -> Unit,
    viewModel: MainViewModel,
) {
    val ip by viewModel.ip
    val apiKey by viewModel.apiKey
    val appSettingsUiState by viewModel.appSettingsUiState.collectAsState()
    val isDarkTheme = isSystemInDarkTheme()
    val colorFilter =
        if (isDarkTheme) ColorFilter.tint(androidx.compose.ui.graphics.Color.Gray) else null

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_foreground),
            contentDescription = "2c Image",
            modifier = Modifier.size(200.dp),
            colorFilter = colorFilter
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Setup your Miner", color = MaterialTheme.colorScheme.onBackground)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Ip example : ", color = MaterialTheme.colorScheme.onBackground)
        Text(text = "http://192.168.1.121/", color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "ApiKey example (32 chars): ", color = MaterialTheme.colorScheme.onBackground)
        Text(
            text = "asdfasdfasdfasdfasdfasdfasdfabtc",
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = ip,
            onValueChange = { viewModel.updateIp(it) },
            label = { Text("Miner IP or DNS", color = MaterialTheme.colorScheme.onBackground) },
            placeholder = {
                Text(
                    "Miner IP or DNS",
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
        )

        OutlinedTextField(
            value = apiKey,
            onValueChange = { viewModel.updateApiKey(it) },
            label = { Text("API Key", color = MaterialTheme.colorScheme.onBackground) },
            placeholder = { Text("API Key", color = MaterialTheme.colorScheme.onBackground) },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (viewModel.isValidIpOrDomain(ip)) {
                    viewModel.setIp(ip)
                    viewModel.updateIp(ip)
                    viewModel.updateApiKey(apiKey)
                    viewModel.checkConnectivityAndSave { isConnected ->
                        if (isConnected) {
                            viewModel.updateIp(ip)
                            viewModel.updateApiKey(apiKey)
                            Log.d("SettingsScreen", "IP: $ip and API Key: $apiKey saved")
                            Toast.makeText(viewModel.context, "Settings saved", Toast.LENGTH_SHORT)
                                .show()
                            val apiService = DynamicApiFactory.create(ip)
                            Log.d("SettingsScreen", "API Service created $apiService")
                            onWizardComplete()
                        } else {
                            Toast.makeText(
                                viewModel.context,
                                "Error ${appSettingsUiState.errorMsg}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                } else {
                    Toast.makeText(viewModel.context, "Please check a valid ip with prefix http(s)://xxx.xxx.xxx.xxx or valid domain", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(NatioOrange40),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}