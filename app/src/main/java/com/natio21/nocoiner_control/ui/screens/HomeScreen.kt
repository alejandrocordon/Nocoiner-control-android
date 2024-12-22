package com.natio21.nocoiner_control.ui.screens

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.natio21.nocoiner_control.R
import kotlinx.coroutines.launch

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
            Text(text = "Temperatura Actual: $currentTemperature °C", style = MaterialTheme.typography.titleMedium)
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
fun HomeScreen(minerInfo: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Control de Temperatura", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Lógica para subir temperatura */ },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Subir Temperatura")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Lógica para bajar temperatura */ },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Bajar Temperatura")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Información del Nocoiner", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Información del Nocoiner: $minerInfo", fontSize = 24.sp, fontWeight = FontWeight.Bold)
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

