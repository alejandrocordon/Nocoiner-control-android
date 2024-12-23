package com.natio21.nocoiner_control

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.natio21.nocoiner_control.MinerInfo
import androidx.lifecycle.lifecycleScope
import com.natio21.nocoiner_control.openapi.client.models.TempSensor
import com.natio21.nocoiner_control.openapi.client.models.TempSensorStatus
import com.natio21.nocoiner_control.openapi.client.models.UnlockRequest
import com.natio21.nocoiner_control.ui.screens.HomeScreen
import com.natio21.nocoiner_control.ui.theme.NocoinercontrolTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var minerApiService: MinerApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        //get summary from miner and wait to get the data
        lifecycleScope.launch {
            val summary = minerApiService.getSummary("asdfasdfasdfasdfasdfasdfasdfabtc")
            Log.d("MainActivity", "Summary: $summary")



            //get http connection to http://192.168.1.121/api/v1/summary and Log.d the data

        }




        // Realizar una llamada a la API en un coroutine scope
        // No bloquear el hilo principal: usamos lifecycleScope.launch
        lifecycleScope.launch {
            try {
                // Llamada a la API (ejemplo: obtener la temperatura actual)
                // get info from miner api using MinerApiService
                //val minerInfo = minerApiService.getMinerInfo()
                //Log.d("MainActivity", "Miner Info: $minerInfo")


                //val status = minerApiService.getMinerStatus()
                //Log.d("MainActivity", "Miner Status: ${status.minerState} Hashrate: ${status.minerStateTime} Description ${status.description}" )

                try {
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

                val summary = minerApiService.getSummary("asdfasdfasdfasdfasdfasdfasdfabtc")

                Log.d("MainActivity", "Summary: $summary")

                summary.miner.chains.let {
                    Log.d("MainActivity", "Chains: $it" )
                }


                summary.miner.let {
                    Log.d("MainActivity", "Chip Temperature: ${it.chipTemp}°C pcb Temperature: ${it.pcbTemp} °C" )
                }
                summary.miner.pcbTemp.let {
                    Log.d("MainActivity", "PCB Temperature: $it °C" )
                }


                summary.miner.let {
                    Log.d("MainActivity", "Chip Temperature: ${it.chipTemp}°C pcb Temperature: ${it.pcbTemp} °C" )
                }

                //val temperature = minerApiService.getTemperatureStatus()
//
                //// Imprimir las temperaturas
                //Log.d("MainActivity", "Chip Temperature: ${temperature.chipTemp}°C pcb Temperature: ${temperature.pcbTemp} °C" )



                setContent {
                    NocoinercontrolTheme {
                        // Aquí podrías cargar tu NocoinerApp o una pantalla principal
                        // Por ejemplo, HomeScreen() o NocoinerApp()

                        HomeScreen(minerInfo = summary.toString())



                    }
                }


                // Podrías actualizar algún estado Compose con estos datos
                // o pasarlos a un ViewModel que exponga un StateFlow o LiveData.
            } catch (e: Exception) {
                Log.e("MainActivity", "Error al obtener temperatura", e)
            }
        }
    }
}


/*import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.natio21.nocoiner_control.databinding.ActivityMainBinding
import com.natio21.nocoiner_control.di.NsdServiceManager
import com.natio21.nocoiner_control.openapi.client.infrastructure.ApiClient
import com.natio21.nocoiner_control.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var nsdServiceManager: NsdServiceManager
    private val homeViewModel: HomeViewModel by viewModels()

    // Crear el cliente al iniciar la Activity
    private val apiClient by lazy {
        ApiClient().apply {
            // Ajusta la URL base de tu API
            basePath = "http://192.168.1.121"
        }
    }

    @Inject
    lateinit var minerApiService: MinerApiService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Call the API to get miner info
        lifecycleScope.launch {
            homeViewModel.minerInfo.collect { minerInfo ->
                minerInfo?.let {
                    Log.d("MinerInfo", it.toString())
                }



            }
        }


        // Realizar una llamada a la API en un coroutine scope
        // No bloquear el hilo principal: usamos lifecycleScope.launch
        lifecycleScope.launch {
            try {
                // Llamada a la API (ejemplo: obtener la temperatura actual)
                val temperatureStatus = service.getTemperatureStatus()
                Log.d("MainActivity", "Temperatura actual: ${temperatureStatus.current}, Objetivo: ${temperatureStatus.target}")

                // Podrías actualizar algún estado Compose con estos datos
                // o pasarlos a un ViewModel que exponga un StateFlow o LiveData.
            } catch (e: Exception) {
                Log.e("MainActivity", "Error al obtener temperatura", e)
            }
        }


        nsdServiceManager = NsdServiceManager(application)
        nsdServiceManager.discoverServices()

       // Para obtener la lista de servicios descubiertos:
       //val antminerServices = nsdServiceManager.getDiscoveredServices()
       //  Log.d("AntminerServices", antminerServices.toString())

    }

    override fun onStop() {
        nsdServiceManager.stopDiscovery()
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}*/


