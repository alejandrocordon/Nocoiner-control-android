package com.natio21.nocoiner_control.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerDefaults.backgroundColor
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.natio21.nocoiner_control.MainViewModel
import com.natio21.nocoiner_control.R
import com.natio21.nocoiner_control.openapi.client.models.CoolingSettings
import com.natio21.nocoiner_control.openapi.client.models.MinerSettings
import com.natio21.nocoiner_control.openapi.client.models.ModeSettings
import com.natio21.nocoiner_control.openapi.client.models.SettingsRequest
import com.natio21.nocoiner_control.ui.theme.NatioOrange40
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BasicHeaterScreen(
    viewModel: MainViewModel,
) {
    val uiState by viewModel.basicUiState.collectAsState()
    val advancedState by viewModel.advancedUiState.collectAsState()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        item {
            DisposableEffect(Unit) {
                val job = viewModel.viewModelScope.launch {
                    while (true) {
                        viewModel.getSummary()
                        delay(5000)
                    }
                }
                onDispose {
                    job.cancel()
                }
            }
            Image(
                painter = painterResource(id = R.drawable.dosc),
                contentDescription = "2c Image",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Temperature MatrixDashboardCard
        val temperatureInfo: List<String> = listOf(
            "${advancedState.summary?.miner?.pcb_temp?.max}ºC",
            "${advancedState.summary?.miner?.chip_temp?.max}ºC"
        )
        val temperatureTitles: List<String> = listOf("PCB Temp", "Chip Temp")
        val temperatureMatrix = listOf(temperatureTitles, temperatureInfo)
        item { MatrixDashboardCard(title = "Temperature", dataMatrix = temperatureMatrix) }


        // Power MatrixDashboardCard
        val powerInfo: List<String> = listOf(
            "${advancedState.summary?.miner?.power_consumption}W"
        )
        val powerTitles: List<String> = listOf("Power Consumption", "Efficiency")
        val powerMatrix = listOf(powerTitles, powerInfo)
        item { MatrixDashboardCard(title = "Power", dataMatrix = powerMatrix) }



        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    Log.d("HomeScreen", "Temperatura al maximo 80ºC")
                    viewModel.setTemperature(80)
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
                    viewModel.setTemperature(70)
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
                    throw RuntimeException("Test Crash") // Force a crash
                    viewModel.setTemperature(65)

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
    Spacer(modifier = Modifier.height(16.dp))


    //Row {
    //    IconButton(onClick = {
    //        // Lógica para bajar
    //        viewModel.changeTemperature(uiState.currentTemperature - 1)
    //    }) {
    //        Icon(Icons.Default.Delete, contentDescription = "Decrease")
    //    }
    //    IconButton(onClick = {
    //        // Lógica para subir
    //        viewModel.changeTemperature(uiState.currentTemperature + 1)
    //    }) {
    //        Icon(Icons.Default.Add, contentDescription = "Increase")
    //    }
    //}
//
    //OutlinedTextField(
    //    value = uiState.timerMinutes.toString(),
    //    onValueChange = { newValue ->
    //        // Manejo de conversion a Int
    //        viewModel.updateTimer(newValue.toIntOrNull() ?: 0)
    //    },
    //    label = { Text("Timer (minutes)") }
    //)
//
    //Button(
    //    onClick = {
    //        viewModel.startTimer {
    //            // al finalizar, puedes mostrar un snackbar o algo
    //        }
    //    }
    //) {
    //    Text("Start Timer")
    //}


    // Manejo de snackbars
    //if (uiState.showSuccessMessage) {
    //    // Ejemplo con SnackbarHost
    //    SnackbarHost(
    //        hostState = remember { SnackbarHostState() },
    //        snackbar = {
    //            Snackbar {
    //                Text("Temperature Updated!")
    //            }
    //        }
    //    )
    //}

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
    if (uiState.showSuccessMessage) {
        SnackbarHost(
            hostState = remember { SnackbarHostState() },
            snackbar = {
                Snackbar {
                    Text("Temperature Updated!")
                }
            }
        )
    }

}
