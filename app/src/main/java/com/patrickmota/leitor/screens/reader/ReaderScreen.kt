package com.patrickmota.leitor.screens.reader

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@Composable
fun ReaderScreen(navController: NavController, url: String) {
    val isLoading = remember {
        mutableStateOf(true)
    }
    Content(url = url, isLoading = {isLoading.value = false})
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading.value){
            Text(text = "Carregando...", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
    IconButton(onClick = { navController.popBackStack() }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back button",
            tint = Color.White,
            modifier = Modifier
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(7.dp)
        )
    }
}

@Composable
fun Content(url: String, isLoading: () -> Unit) {
    val newUrl = url.replace("{", "").replace("}", "")

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                    isLoading()
                }
            }
            loadUrl(newUrl)
        }
    }, update = {
        it.loadUrl(newUrl)
    })
}
