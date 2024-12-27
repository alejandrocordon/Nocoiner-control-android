package com.natio21.nocoiner_control.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.natio21.nocoiner_control.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onExitRequested: () -> Unit
) {
    val navController = rememberNavController()

    // Al pulsar atrás, mostrar un diálogo
    BackHandler {
        // Muestras un diálogo pidiendo confirmación
        onExitRequested()
    }

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainRoutes.Basic.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(MainRoutes.Basic.route) {
                BasicHeaterScreen(viewModel)
            }
            composable(MainRoutes.Advanced.route) {
                AdvancedScreen(viewModel)
                AdvancedScreen(viewModel)
            }
            composable(MainRoutes.Settings.route) {
                SettingsScreen(viewModel)
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        MainRoutes.Basic,
        MainRoutes.Advanced,
        MainRoutes.Settings
    )
    NavigationBar {
        val currentDestination = navController.currentDestination?.route
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        // Evitar volver a instanciar destinos
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    // Usa un Icon que represente cada pantalla
                },
                label = { Text(screen.label) }
            )
        }
    }
}

// Rutas internas de Main
sealed class MainRoutes(val route: String, val label: String) {
    object Basic : MainRoutes("basic", "Básico")
    object Advanced : MainRoutes("advanced", "Avanzado")
    object Settings : MainRoutes("settings", "Ajustes")
}