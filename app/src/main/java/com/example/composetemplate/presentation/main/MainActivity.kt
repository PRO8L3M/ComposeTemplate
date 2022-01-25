package com.example.composetemplate.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetemplate.ui.theme.ComposeTemplateTheme
import com.example.dashboard.main.ui.Dashboard
import com.example.dashboard.useroverview.ui.UserOverview
import com.example.navigation.Routes
import com.example.navigation.extensions.navigateToDestination
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeTemplateTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Dashboard.route
                    ) {
                        composable(Routes.Dashboard.route) {
                            Dashboard(navController::navigateToDestination)
                        }

                        composable(Routes.UserOverview.route) {
                            UserOverview()
                        }
                    }
                }
            }
        }
    }
}
/*

@Composable
fun DashboardScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: MainViewModel = hiltViewModel()
    val themeMode: Int by viewModel.themeMode.collectAsState()
    DashboardPage(
        themeMode,
        {
            navigator.navigate(DashboardNavDestination)
            viewModel.setThemeMode(MainViewModel.LIGHT_MODE)
        },
        {
            viewModel.setThemeMode(MainViewModel.DARK_MODE)
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
}*/
