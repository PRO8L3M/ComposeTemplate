package com.example.composetemplate.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composetemplate.presentation.main.destinations.DashboardScreenDestination
import com.example.composetemplate.presentation.main.destinations.ThemeScreenDestination
import com.example.composetemplate.ui.theme.ComposeTemplateTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                Surface(color = MaterialTheme.colors.background) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}

@Destination
@Composable
fun ThemeScreen(
    currentValue: Int
) {
    Text(text = "Current value: $currentValue")
}

@Destination(start = true)
@Composable
fun DashboardScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: MainViewModel = hiltViewModel()
    val themeMode: Int by viewModel.themeMode.collectAsState()
    DashboardPage(
        themeMode,
        {
            viewModel.setThemeMode(MainViewModel.LIGHT_MODE)
            navigator.navigate(ThemeScreenDestination(themeMode))
        },
        {
            viewModel.setThemeMode(MainViewModel.DARK_MODE)
            navigator.navigate(ThemeScreenDestination(themeMode))
        }
    )
}

@Preview
@Composable
fun DashboardPagePreview() {
    DashboardPage()
}

@Composable
fun DashboardPage(
    themeMode: Int = -1,
    onLightThemeClick: () -> Unit = {},
    onDarkThemeClick: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Text(
            fontSize = 20.sp,
            text = buildAnnotatedString {
                append("Theme mode: ")
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                ) {
                    append(themeMode.toString())
                }
            }
        )
        Spacer(modifier = Modifier.height(50.dp))
        ThemeButton(text = "LIGHT THEME", onLightThemeClick)
        Spacer(modifier = Modifier.height(8.dp))
        ThemeButton(text = "DARK THEME", onDarkThemeClick)
    }
}

@Preview
@Composable
fun ThemeButtonPreview() {
    ThemeButton(text = "Test button")
}


@Composable
fun ThemeButton(text: String, onClick: () -> Unit = {}) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}