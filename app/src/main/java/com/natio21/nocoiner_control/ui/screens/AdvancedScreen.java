package com.natio21.nocoiner_control.ui.screens;

import androidx.compose.runtime.Composable;
import androidx.test.services.storage.file.PropertyFile;

@Composable
fun(viewModel: MainViewModel) {
    val advancedState = viewModel.advancedUiState

    PropertyFile.Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
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
                val ip = advancedState.minerIp
                // Abre navegador externo (Android Intent)
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