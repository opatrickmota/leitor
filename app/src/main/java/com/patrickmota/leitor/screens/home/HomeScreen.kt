package com.patrickmota.leitor.screens.home

import android.app.Activity
import android.content.Context
import android.webkit.URLUtil.isValidUrl
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patrickmota.leitor.R
import com.patrickmota.leitor.model.Leitor
import com.patrickmota.leitor.navigation.LeitorScreens
import com.patrickmota.leitor.ui.theme.LightGray
import com.patrickmota.leitor.ui.theme.Purple200
import com.patrickmota.leitor.ui.theme.Purple500
import com.patrickmota.leitor.ui.theme.Red
import com.patrickmota.leitor.utils.RippleCustomTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = getViewModel()) {
    val context = LocalContext.current
    var url by remember { mutableStateOf("") }
    var error by remember {
        mutableStateOf(false)
    }
    val urls = remember {
        mutableStateOf<List<Leitor>>(emptyList())
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            homeViewModel.getUrls().collect {
                urls.value = it
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Text(
            text = context.resources.getString(R.string.title),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Purple500,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        TextField(
            value = url,
            onValueChange = { url = it },
            label = {
                Text(
                    text = context.resources.getString(R.string.url),
                    color = if (error) Color.Red else Color.Blue
                )
            },
            placeholder = {
                Text(
                    text = context.resources.getString(R.string.url_example),
                    color = if (error) Color.Red else Color.Blue
                )
            },
            isError = error,
            colors = TextFieldDefaults.textFieldColors(
                textColor = if (error) Color.Red else Color.Blue,
                focusedIndicatorColor = if (error) Color.Red else Color.Blue,
                unfocusedIndicatorColor = if (error) Color.Red else Color.Blue,
                backgroundColor = LightGray,
            )
        )
        Button(
            onClick = {
                if (isValidUrl(url)) {
                    error = false
                    navigateToContent(homeViewModel, url, navController)
                } else {
                    error = true
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Purple500)
        ) {
            Text(text = context.resources.getString(R.string.read_content))
        }

        ListAccessedUrl(homeViewModel = homeViewModel, navController = navController, urls.value)
    }

    FinishApplication(context = context)
}


@Composable
fun ListAccessedUrl(
    homeViewModel: HomeViewModel,
    navController: NavController,
    urls: List<Leitor>
) {

    LazyColumn {
        items(items = urls) { item ->

            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    onClick = {
                        homeViewModel.deleteUrl(item)

                        navigateToContent(
                            homeViewModel = homeViewModel,
                            url = item.url,
                            navController = navController
                        )
                    },
                    modifier = Modifier.weight(0.8f)
                ) {
                    Text(
                        text = item.url,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
                    IconButton(
                        onClick = {
                            homeViewModel.deleteUrl(item)
                        },
                        interactionSource = interactionSource,
                        modifier = Modifier
                            .weight(0.2f)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete url",
                            tint = if (isPressed) Red else Purple200
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FinishApplication(context: Context) {
    BackHandler {
        val activity = (context as? Activity)
        activity?.finish()
    }
}

private fun navigateToContent(
    homeViewModel: HomeViewModel,
    url: String,
    navController: NavController,
    addUrl: Boolean = true
) {
    if (addUrl) {
        homeViewModel.addUrl(Leitor(url = url))
    }
    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
    navController.navigate("${LeitorScreens.ReaderScreen.name}/{$encodedUrl}")
}
