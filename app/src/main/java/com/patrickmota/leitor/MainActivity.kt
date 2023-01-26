package com.patrickmota.leitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.patrickmota.leitor.navigation.LeitorNavigation
import com.patrickmota.leitor.ui.theme.LeitorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeitorTheme {
                LeitorNavigation()
            }
        }
    }
}
