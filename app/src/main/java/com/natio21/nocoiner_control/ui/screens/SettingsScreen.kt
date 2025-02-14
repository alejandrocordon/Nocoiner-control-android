package com.natio21.nocoiner_control.ui.screens;

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.navigation.NavController
import com.natio21.nocoiner_control.DynamicApiFactory
import com.natio21.nocoiner_control.MainViewModel
import com.natio21.nocoiner_control.R
import com.natio21.nocoiner_control.ui.theme.NatioOrangeDD
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.graphics.Color

@Composable
fun SettingsScreen(viewModel: MainViewModel, navController: NavController) {
    val ip by viewModel.ip
    val apiKey by viewModel.apiKey
    val appSettingsUiState by viewModel.appSettingsUiState.collectAsState()
    val isDarkTheme = isSystemInDarkTheme()
    val colorFilter =
        if (isDarkTheme) ColorFilter.tint(androidx.compose.ui.graphics.Color.Gray) else null

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        item {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "2c Image",
                modifier = Modifier.size(100.dp),
                colorFilter = colorFilter
            )
            Text(
                text = "Setup your Miner",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Current Ip: ${viewModel.getIpFromPrefs()}",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Current ApiKey:",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "${viewModel.getApiKeyFromPrefs()}",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        item {
            OutlinedTextField(
                value = ip,
                onValueChange = { viewModel.updateIp(it) },
                label = { Text("Miner IP or DNS") },
                placeholder = { Text("Miner IP or DNS") },
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = apiKey,
                onValueChange = { viewModel.updateApiKey(it) },
                label = { Text("API Key") },
                placeholder = { Text("API Key") },
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
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
                                Toast.makeText(
                                    viewModel.context,
                                    "Settings saved",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val apiService = DynamicApiFactory.create(ip)
                                Log.d("SettingsScreen", "API Service created $apiService")
                                navController.navigate(MainRoutes.Basic.route)
                            } else {
                                Toast.makeText(
                                    viewModel.context,
                                    "Error ${appSettingsUiState.errorMsg}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(
                            viewModel.context,
                            "Please check a valid ip with prefix http(s)://xxx.xxx.xxx.xxx or valid domain",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(NatioOrangeDD),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Button(
                onClick = {
                    viewModel.updateIp("")
                    viewModel.updateApiKey("")
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(NatioOrangeDD),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Clear data", color = Color.White)
            }
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            Text(
                text = "App version: ${viewModel.getAppVersion()}",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}