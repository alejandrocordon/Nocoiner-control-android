package com.natio21.nocoiner_control.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import com.natio21.nocoiner_control.MinerApiService
import com.natio21.nocoiner_control.R
import com.natio21.nocoiner_control.openapi.client.models.CoolingSettings
import com.natio21.nocoiner_control.openapi.client.models.MinerSettings
import com.natio21.nocoiner_control.openapi.client.models.ModeSettings
import com.natio21.nocoiner_control.openapi.client.models.SettingsRequest
import com.natio21.nocoiner_control.openapi.client.models.SettingsResponse
import com.natio21.nocoiner_control.ui.theme.NatioOrange40
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    lifecycleScope: LifecycleCoroutineScope,
    minerApiService: MinerApiService
) {

    var temperature by remember { mutableStateOf(0) }
    var settingsResponse by remember { mutableStateOf<SettingsResponse?>(null) }

    // Make the API call to get settings
    LaunchedEffect(Unit) {
        try {
            settingsResponse = minerApiService.getSettings("asdfasdfasdfasdfasdfasdfasdfabtc")
            val cooling = settingsResponse!!.miner.cooling
            Log.d("HomeScreen", "Cooling Mode: ${cooling.mode.name}, Param: ${cooling.mode.param}")
            Log.d("HomeScreen", "Fan Min Count: ${cooling.fan_min_count}, Fan Min Duty: ${cooling.fan_min_duty}")
            temperature = cooling.mode.param
        } catch (e: Exception) {
            Log.e("HomeScreen", "Error fetching settings", e)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Add the image
        Image(
            painter = painterResource(id = R.drawable.dosc),
            contentDescription = "2c Image",
            modifier = Modifier.size(200.dp)
        )
        Text(stringResource(id = R.string.control_temperature), fontSize = 14.sp, fontWeight = FontWeight.Normal)
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(id = R.string.current_temperature_label, temperature), fontSize = 14.sp, fontWeight = FontWeight.Normal)
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Log.d("HomeScreen", "Temperatura al maximo 80ºC")
                lifecycleScope.launch {
                    try {
                        val settingsRequest = SettingsRequest(
                            miner = MinerSettings(
                                cooling = CoolingSettings(
                                    mode = ModeSettings(name = "auto", param = 80),
                                    fan_min_count = 4,
                                    fan_min_duty = 10
                                )
                            )
                        )
                        settingsResponse = minerApiService.updateSettings(
                            apiKey = "asdfasdfasdfasdfasdfasdfasdfabtc",
                            request = settingsRequest
                        )
                        temperature = 80
                        Log.d("MainActivity", "Settings Response: $temperature")
                    } catch (e: Exception) {
                        Log.e("MainActivity", "Error al actualizar la configuración", e)
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(NatioOrange40),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.max_temperature))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Log.d("HomeScreen", "Temperatura al maximo 70ºC")
                lifecycleScope.launch {
                    try {
                        val settingsRequest = SettingsRequest(
                            miner = MinerSettings(
                                cooling = CoolingSettings(
                                    mode = ModeSettings(name = "auto", param = 70),
                                    fan_min_count = 4,
                                    fan_min_duty = 10
                                )
                            )
                        )
                        val settingsResponse = minerApiService.updateSettings(
                            apiKey = "asdfasdfasdfasdfasdfasdfasdfabtc",
                            request = settingsRequest
                        )
                        temperature = 70
                        Log.d("MainActivity", "Settings Response: $temperature")
                    } catch (e: Exception) {
                        Log.e("MainActivity", "Error al actualizar la configuración", e)
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(NatioOrange40),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.medium_temperature))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Log.d("HomeScreen", "Temperatura al minimo 65ºC")
                lifecycleScope.launch {
                    try {
                        val settingsRequest = SettingsRequest(
                            miner = MinerSettings(
                                cooling = CoolingSettings(
                                    mode = ModeSettings(name = "auto", param = 65),
                                    fan_min_count = 4,
                                    fan_min_duty = 10
                                )
                            )
                        )
                        val settingsResponse = minerApiService.updateSettings(
                            apiKey = "asdfasdfasdfasdfasdfasdfasdfabtc",
                            request = settingsRequest
                        )
                        temperature = 65
                        Log.d("MainActivity", "Settings Response: $temperature")
                    } catch (e: Exception) {
                        Log.e("MainActivity", "Error al actualizar la configuración", e)
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(NatioOrange40),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.min_temperature))
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}