package com.patrickmota.leitor.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patrickmota.leitor.R
import com.patrickmota.leitor.navigation.LeitorScreens
import com.patrickmota.leitor.ui.theme.Purple500
import com.patrickmota.leitor.utils.NavigationBarColor
import com.patrickmota.leitor.utils.StatusBarColor

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Leitor", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = Purple500)
    }

    navController.navigate(LeitorScreens.HomeScreen.name)

    StatusBarColor(color = R.color.black)
    NavigationBarColor(color = R.color.black)
}