package com.natio21.nocoiner_control

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.natio21.nocoiner_control.openapi.client.models.UnlockRequest
import com.natio21.nocoiner_control.ui.screens.AppNavHost
import com.natio21.nocoiner_control.ui.theme.NocoinercontrolTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //@Inject
    //lateinit var minerApiService: MinerApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //lifecycleScope.launch {
            /*try {
                val apikeysApi = minerApiService.getApiKeys("asdfasdfasdfasdfasdfasdfasdfabtc")
                apikeysApi.forEach() {
                    Log.d("MainActivity", "API Keys: ${it.key} ${it.description}")
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error al obtener API Keys", e)
            }

            try {
                val unlockRequest = UnlockRequest("admin")
                val unlock = minerApiService.unlockMiner(unlockRequest)
                Log.d("MainActivity", "token: ${unlock.token}")
            } catch (e: Exception) {
                Log.e("MainActivity", "Error al desbloquear el miner", e)
            }

            try {
                val authCheck = minerApiService.getAuthCheck("asdfasdfasdfasdfasdfasdfasdfabtc")
                Log.d("MainActivity", "API Keys: $authCheck")
            } catch (e: Exception) {
                Log.e("MainActivity", "Error al obtener API Keys", e)
            }

            try {
                val summary = minerApiService.getSummary("asdfasdfasdfasdfasdfasdfasdfabtc")

                Log.d("MainActivity", "Summary: $summary")
                summary.miner.minerStatus?.let {
                    Log.d(
                        "MainActivity",
                        "Miner Status: ${it.minerState} Hashrate: ${it.minerStateTime} Description ${it.description}"
                    )
                }
                summary.miner.minerType.let {
                    Log.d("MainActivity", "Miner Type: $it")
                }
                summary.miner.chipTemp.let {
                    Log.d("MainActivity", "Chip Temp: $it")
                }
                summary.miner.chains.forEach { chain ->
                    Log.d(
                        "MainActivity",
                        "Chain: ${chain.id} Frequency: ${chain.frequency} Voltage: ${chain.voltage} Chip Temp: ${chain.chipTemp}"
                    )
                }

                val chains = minerApiService.getChains()
                Log.d("MainActivity", "Chains: $chains")
                chains.forEach {
                    Log.d("MainActivity", "id : ${it.id}")
                    Log.d("MainActivity", "frequency : ${it.frequency}")
                    Log.d("MainActivity", "voltage : ${it.voltage}")
                    Log.d("MainActivity", "chipTemp : ${it.chipTemp}")
                }*/

            setContent {
                NocoinercontrolTheme {
                    //HomeScreen(
                    //    lifecycleScope = lifecycleScope,
                    //    minerApiService = minerApiService
                    //)


                    val navController = rememberNavController()
                    //val viewModel: MainViewModel = viewModel()
                    val viewModel: MainViewModel = hiltViewModel()
                    AppNavHost(navController = navController, viewModel = viewModel)

                }


            }
            //} catch (e: Exception) {
            //    Log.e("MainActivity", "Error al obtener temperatura", e)
            //}
        //}
    }
}
