package com.patrickmota.leitor.screens.home

import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.patrickmota.leitor.navigation.LeitorScreens
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    var url by remember { mutableStateOf("") }
    var error by remember {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(18.dp)
    ) {
        TextField(
            value = url,
            onValueChange = { url = it },
            label = { Text(text = "URL") },
            isError = error
        )
        Button(onClick = {
            if (url.isNotBlank()) {
                error = false
                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())

                navController.navigate("${LeitorScreens.ReaderScreen.name}/{$encodedUrl}")
            }
            error = true
        }) {
            Text(text = "Ler conteúdo")
        }
    }

    FinishApplication(context = context)
}

@Composable
fun FinishApplication(context: Context) {
    BackHandler {
        val activity = (context as? Activity)
        activity?.finish()
    }
}
