package com.example.composetemplate.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.example.composetemplate.navigation.MainNavHost
import com.example.composetemplate.ui.theme.ComposeTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeTemplateTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainNavHost(navController = navController)
                }
            }
        }
    }
}