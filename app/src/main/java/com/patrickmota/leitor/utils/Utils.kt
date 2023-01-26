package com.patrickmota.leitor.utils

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat

@Composable
fun StatusBarColor(color: Int) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        window.statusBarColor =
            ContextCompat.getColor(LocalContext.current.applicationContext, color)

        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightStatusBars = isSystemInDarkTheme()
        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightNavigationBars = isSystemInDarkTheme()

    }
}

@Composable
fun NavigationBarColor(color: Int) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        window.navigationBarColor =
            ContextCompat.getColor(LocalContext.current.applicationContext, color)

        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightStatusBars = isSystemInDarkTheme()
        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightNavigationBars = isSystemInDarkTheme()

    }
}
