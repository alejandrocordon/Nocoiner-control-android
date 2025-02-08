package com.natio21.nocoiner_control.ui.screens

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.natio21.nocoiner_control.MainViewModel
import com.natio21.nocoiner_control.R
import com.natio21.nocoiner_control.ui.theme.NatioGreenColor40
import com.natio21.nocoiner_control.ui.theme.NatioGreenColor80
import com.natio21.nocoiner_control.ui.theme.NatioOrange40
import com.natio21.nocoiner_control.ui.theme.NatioRedColor40
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BasicHeaterScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val uiState by viewModel.basicUiState.collectAsState()
    val advancedState by viewModel.advancedUiState.collectAsState()
    val isDarkTheme = isSystemInDarkTheme()
    val colorFilter = if (isDarkTheme) ColorFilter.tint(Color.Gray) else null

    viewModel.init()

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
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "2c Image",
                modifier = Modifier.size(100.dp),
                colorFilter = colorFilter
            )
            Spacer(modifier = Modifier.height(32.dp))
        }


        val hashrate = advancedState.summary?.miner?.average_hashrate?.let {
            String.format("%.2f", it.toDouble())
        } ?: "N/A"
        val temperatureInfo = "${advancedState.summary?.miner?.chip_temp?.max}"


        item {

            Text(
                text = buildAnnotatedString {
                    append("Hashrate: ")
                    withStyle(style = SpanStyle(fontSize = 24.sp, color = NatioOrange40)) {
                        append("$hashrate TH/s")
                    }
                },
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = buildAnnotatedString {
                    append("Temperatura del chip: ")
                    withStyle(style = SpanStyle(fontSize = 24.sp, color = NatioOrange40)) {
                        append("$temperatureInfo ºC")
                    }
                },
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(64.dp))
            Button(
                onClick = {
                    Log.d("HomeScreen", "Temperatura del chip 80ºC")
                    viewModel.setTemperature(80)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(NatioOrange40),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Modo calefacción",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        "Temperatura del chip 80ºC",
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    Log.d("HomeScreen", "Temperatura del chip 72ºC")
                    viewModel.setTemperature(72)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(NatioOrange40),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Modo intermedio",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        "Temperatura del chip 72ºC",
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    Log.d("HomeScreen", "Temperatura del chip 65ºC")
                    viewModel.setTemperature(65)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(NatioOrange40),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Modo eficiencia",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        "Temperatura del chip 65ºC",
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.pauseMinnig()
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(NatioRedColor40),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Sleep",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        "pause minning",
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.resumeMinnig()
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(NatioGreenColor80),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Resume",
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        "resume minning",
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
