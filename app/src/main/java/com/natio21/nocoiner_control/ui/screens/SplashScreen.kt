package com.natio21.nocoiner_control.ui.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.natio21.nocoiner_control.MainViewModel

@Composable
fun SplashScreen(
    onWizardNeeded: () -> Unit,
    onGoToMain: () -> Unit,
    context: Context,
    viewModel: MainViewModel
) {
    // Dibuja tu logo, etc.
    // Consultar SharedPreferences (o un estado en el ViewModel).
    // Simulas un retardo o esperas a un stateFlow en el ViewModel que cargue la config.

    LaunchedEffect(Unit) {
        // Haz la comprobación de IP y API Key
        val hasData = viewModel.hasSavedData()
        if (hasData) {
            viewModel.setIp(viewModel.getIp().toString())
            onGoToMain()
        } else {
            onWizardNeeded()
        }
    }

    // Diseño del Splash (logo, fondo, etc.)
    // ...
}
