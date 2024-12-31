package com.natio21.nocoiner_control.ui.screens;

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.natio21.nocoiner_control.MainViewModel
import com.natio21.nocoiner_control.Pool


@Composable
fun AdvancedScreen(viewModel: MainViewModel) {
    val advancedState = viewModel.advancedUiState

    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Mining Pools", style = MaterialTheme.typography.titleLarge)

        // Listado de pools
        LazyColumn {
            items(advancedState.pools) { pool ->
                PoolItem(
                    pool = pool,
                    onEditClick = { viewModel.editPool(pool) },
                    onDeleteClick = { viewModel.deletePool(pool) }
                )
            }
        }

        // Botón para añadir pool
        Button(onClick = { viewModel.createNewPool() }) {
            Text("Add Pool")
        }

        // Métricas
        Text("Hashrate: ${advancedState.hashrate}")

        // Botón para ir a la web local
        Button(onClick = {
            //val ip = advancedState.minerIp
            val ip = viewModel.getIp()
            Log.d("AdvancedScreen", "IP: $ip")
            viewModel.openMinerWeb(ip)
        }) {
            Text("Go to Miner Web")
        }
    }
}

@Composable
fun PoolItem(pool: Pool, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
    Row(Modifier.fillMaxWidth()) {
        Text("URL: ${pool.url} Priority: ${pool.priority}")
        IconButton(onClick = onEditClick) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
        IconButton(onClick = onDeleteClick) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
