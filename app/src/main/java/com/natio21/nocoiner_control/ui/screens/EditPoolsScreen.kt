package com.natio21.nocoiner_control.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.natio21.nocoiner_control.MainViewModel
import com.natio21.nocoiner_control.ui.theme.NatioOrange40
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EditPoolsScreen(viewModel: MainViewModel, navController: NavController) {
    val advancedState by viewModel.advancedUiState.collectAsState()
    val poolsData by viewModel.poolsData.collectAsState()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            DisposableEffect(Unit) {
                val job = viewModel.viewModelScope.launch {
                    viewModel.getSummary()
                }
                onDispose {
                    job.cancel()
                }
            }
            Text(text = "Edit Pools", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
        }

        val poolsInfo: List<List<String>> = advancedState.summary?.miner?.pools?.map { pool ->
            listOf(pool.url, pool.pool_type, pool.status, pool.ping.toString())
        } ?: emptyList()


        items(poolsData) { pool ->
            OutlinedTextField(
                value = pool.url,
                onValueChange = {
                    //viewModel.updatePoolData(
                    //    poolsData.indexOf(pool),
                    //    it,
                    //    pool.port
                    //)
                },
                label = { Text("Pool URL ${poolsData.indexOf(pool) + 1}") },
                placeholder = { Text("Enter Pool URL") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = pool.port,
                onValueChange = { /*viewModel.updatePoolData(poolsData.indexOf(pool), pool.url, it)*/ },
                label = { Text("Pool Port ${poolsData.indexOf(pool) + 1}") },
                placeholder = { Text("Enter Pool Port") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Button(
                onClick = {
                    //viewModel.savePools()
                    navController.popBackStack()
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(NatioOrange40),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.popBackStack() },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(NatioOrange40),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel")
            }
        }
    }
}