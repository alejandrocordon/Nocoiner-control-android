package com.natio21.nocoiner_control.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun SplashScreen(
    onWizardNeeded: () -> Unit,
    onGoToMain: () -> Unit,
    viewModel: MainViewModel
) {
    // Dibuja tu logo, etc.
    // Consultar SharedPreferences (o un estado en el ViewModel).
    // Simulas un retardo o esperas a un stateFlow en el ViewModel que cargue la config.

    LaunchedEffect(Unit) {
        // Haz la comprobación de IP y API Key
        val hasData = viewModel.hasSavedData()
        if (hasData) {
            onGoToMain()
        } else {
            onWizardNeeded()
        }
    }

    // Diseño del Splash (logo, fondo, etc.)
    // ...
}
