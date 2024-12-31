package com.natio21.nocoiner_control.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.natio21.nocoiner_control.MainViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(navController, startDestination = Routes.Splash.route) {

        composable(Routes.Splash.route) {
            SplashScreen(
                onWizardNeeded = {
                    navController.navigate(Routes.Wizard.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                },
                onGoToMain = {
                    navController.navigate(Routes.Main.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                },
                context = navController.context,
                viewModel = viewModel
            )
        }

        composable(Routes.Wizard.route) {
            // WizardScreen puede tener su propia navegación interna,
            // o múltiples pantallas en un Page-based approach. Ejemplo:
            WizardScreen(
                onWizardComplete = {
                    navController.navigate(Routes.Main.route) {
                        popUpTo(Routes.Wizard.route) { inclusive = true }
                    }
                },
                viewModel = viewModel
            )
        }

        composable(Routes.Main.route) {
            MainScreen(
                viewModel = viewModel,
                onExitRequested = {
                    // Manejo de confirmación de salida
                }
            )
        }
    }
}

// Rutas
sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Wizard : Routes("wizard")
    object Main : Routes("main")
}