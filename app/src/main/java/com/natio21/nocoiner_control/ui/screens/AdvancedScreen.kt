package com.natio21.nocoiner_control.ui.screens;

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.natio21.nocoiner_control.MainViewModel
import com.natio21.nocoiner_control.R
import com.natio21.nocoiner_control.ui.theme.NatioOrange40
import com.natio21.nocoiner_control.ui.theme.NocoinercontrolTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun AdvancedScreen(viewModel: MainViewModel) {
    val advancedState by viewModel.advancedUiState.collectAsState()
    val isDarkTheme = isSystemInDarkTheme()
    val colorFilter = if (isDarkTheme) ColorFilter.tint(Color.Gray) else null

    NocoinercontrolTheme {
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
                Spacer(modifier = Modifier.height(16.dp))
            }


            // Miner
            val minerTitles: List<String> = listOf("miner", "status")
            val minerInfo: List<String> = listOf(
                "${advancedState.summary?.miner?.miner_type}",
                "${advancedState.summary?.miner?.miner_status?.miner_state}",

                )
            val minerMatrix = listOf(minerTitles, minerInfo)
            item { MatrixDashboardCard(title = "Miner", dataMatrix = minerMatrix) }

            // Hashrate
            val hashrateInfo: List<String> = listOf(
                String.format(
                    "%.2f",
                    advancedState.summary?.miner?.hr_average?.div(1000) ?: 0.0
                ) + "TH/s",
                String.format(
                    "%.2f",
                    advancedState.summary?.miner?.hr_nominal?.div(1000) ?: 0.0
                ) + "TH/s",
                String.format(
                    "%.2f",
                    advancedState.summary?.miner?.hr_realtime?.div(1000) ?: 0.0
                ) + "TH/s",
                "${advancedState.summary?.miner?.found_blocks}"
            )

            val hasrateTitles: List<String> =
                listOf("average", "realtime", "found blocks")
            val hashrateMatix = listOf(hasrateTitles, hashrateInfo)
            item { MatrixDashboardCard(title = "Hashrate", dataMatrix = hashrateMatix) }

            // Temperature MatrixDashboardCard
            val temperatureInfo: List<String> = listOf(
                "${advancedState.summary?.miner?.pcb_temp?.min}ºC-${advancedState.summary?.miner?.pcb_temp?.max}ºC",
                "${advancedState.summary?.miner?.chip_temp?.min}ºC-${advancedState.summary?.miner?.chip_temp?.max}ºC"
            )
            val temperatureTitles: List<String> = listOf("PCB Temp", "Chip Temp")
            val temperatureMatrix = listOf(temperatureTitles, temperatureInfo)
            item { MatrixDashboardCard(title = "Temperature", dataMatrix = temperatureMatrix) }

            // Power MatrixDashboardCard
            val powerInfo: List<String> = listOf(
                "${advancedState.summary?.miner?.power_consumption}W",
                "${advancedState.summary?.miner?.power_efficiency}%"
            )
            val powerTitles: List<String> = listOf("Power Consumption", "Efficiency")
            val powerMatrix = listOf(powerTitles, powerInfo)
            item { MatrixDashboardCard(title = "Power", dataMatrix = powerMatrix) }


            // Pools MatrixDashboardCard
            val poolsTitles: List<String> = listOf("Pool", "Type", "Status", "Lantency")
            val poolsInfo: List<List<String>> = advancedState.summary?.miner?.pools?.map { pool ->
                listOf(pool.url, pool.pool_type, pool.status, pool.ping.toString())
            } ?: emptyList()
            val poolMatrix = listOf(poolsTitles) + poolsInfo
            item { MatrixDashboardCard(title = "Pools", dataMatrix = poolMatrix) }

            // DevFee MatrixDashboardCard
            val devFeeInfo: List<String> = listOf(
                "${advancedState.summary?.miner?.devfee}GH/s",
                "${advancedState.summary?.miner?.devfee_percent}%"
            )
            val devFeeTitles: List<String> = listOf("DevFee", "DevFee Percent")
            val devFeeMatrix = listOf(devFeeTitles, devFeeInfo)
            item { MatrixDashboardCard(title = "DevFee", dataMatrix = devFeeMatrix) }

            // Fans MatrixDashboardCard
            val fansInfo: List<List<String>> =
                listOf(
                    listOf(
                        "${advancedState.summary?.miner?.cooling?.fan_num} Fans",
                        "Duty",
                        "${advancedState.summary?.miner?.cooling?.fan_duty}%"
                    )
                ) +
                        listOf(listOf("", "", "")) +
                        listOf(listOf("id", "RPM", "Status")) +
                        (advancedState.summary?.miner?.cooling?.fans?.map { fan ->
                            listOf(fan.id.toString(), fan.rpm.toString(), fan.status.toString())
                        } ?: emptyList())
            item { MatrixDashboardCard(title = "Fans", dataMatrix = fansInfo) }



            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.createNewPool() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(NatioOrange40),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Pool")
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { viewModel.openMinerWeb() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(NatioOrange40),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Go to Miner Web")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun DashboardCard(title: String, content: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = content, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun MatrixDashboardCard(title: String, dataMatrix: List<List<String>>) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            val longestRowSize = dataMatrix.maxOfOrNull { it.size } ?: 0
            dataMatrix.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    rowItems.forEach { content ->
                        Text(
                            text = content,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentWidth(Alignment.Start)
                        )
                    }
                    repeat(longestRowSize - rowItems.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}