package com.natio21.nocoiner_control.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.natio21.nocoiner_control.MinerApiService
import com.natio21.nocoiner_control.R
import com.natio21.nocoiner_control.openapi.client.models.CoolingSettings
import com.natio21.nocoiner_control.openapi.client.models.MinerSettings
import com.natio21.nocoiner_control.openapi.client.models.ModeSettings
import com.natio21.nocoiner_control.openapi.client.models.SettingsRequest
import com.natio21.nocoiner_control.openapi.client.models.SettingsResponse
import com.natio21.nocoiner_control.ui.theme.NatioOrange40
import com.natio21.nocoiner_control.ui.theme.NatioOrange80
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    currentTemperature: Double? = null,
    onRefreshClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenido a la app Nocoiner", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(24.dp))

        if (currentTemperature == null) {
            Text(text = "Cargando temperatura...")
        } else {
            Text(
                text = "Temperatura Actual: $currentTemperature °C",
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onRefreshClick) {
            Text("Refrescar Datos")
        }
    }
}

@Composable
fun NocoinerApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavigationHost(navController)
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen() }
        composable("settings") { SettingsScreen() }
    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.natio21),
            contentDescription = "Nocoiner Logo",
            modifier = Modifier.size(200.dp)
        )
    }
    // Simulate splash delay
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        kotlinx.coroutines.delay(2000)
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }
}

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
        Text("Control de Temperatura", fontSize = 14.sp, fontWeight = FontWeight.Normal)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Temperatura Actual: $temperature°C", fontSize = 14.sp, fontWeight = FontWeight.Normal)
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
            Text("Temperatura Máxima")
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
            Text("Temperatura Media")
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
            Text("Temperatura Mínima")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Pantalla de Configuración", fontSize = 20.sp)
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(backgroundColor = Color.Gray) {
        BottomNavigationItem(
            selected = false,
            onClick = { navController.navigate("home") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.natio21),
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") },
            alwaysShowLabel = true
        )
        BottomNavigationItem(
            selected = false,
            onClick = { navController.navigate("settings") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.natio21),
                    contentDescription = "Settings"
                )
            },
            label = { Text("Settings") },
            alwaysShowLabel = true
        )
    }
}

