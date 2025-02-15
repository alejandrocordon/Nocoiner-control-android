package com.natio21.nocoiner_control.ui.screens

import android.util.Log
import androidx.compose.foundation.background
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
import com.natio21.nocoiner_control.ui.theme.NatioOrangeDD
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.natio21.nocoiner_control.ui.theme.NatioGrey88
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EditPoolsScreen(viewModel: MainViewModel, navController: NavController) {
    val advancedState by viewModel.advancedUiState.collectAsState()
    val pools = advancedState.settings?.miner?.pools ?: emptyList()

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

                    viewModel.getSummaryAndSettings()
                }
                onDispose {
                    job.cancel()
                }
            }
            Text(text = "Edit Pools", style = MaterialTheme.typography.titleLarge,color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(16.dp))
        }


        items(pools) { pool ->
            OutlinedTextField(
                value = pool.url,
                onValueChange = {  },
                label = { Text("URL ${pools.indexOf(pool)}") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = pool.user,
                onValueChange = {  },
                label = { Text("User ${pools.indexOf(pool)}") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = pool.pass,
                onValueChange = { },
                label = { Text("Pass ${pools.indexOf(pool)}") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider(color = NatioGrey88, thickness = 2.dp)
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Button(
                onClick = {
                    //viewModel.savePools()
                    navController.popBackStack()
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(NatioOrangeDD),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save",color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.popBackStack() },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(NatioOrangeDD),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel",color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}