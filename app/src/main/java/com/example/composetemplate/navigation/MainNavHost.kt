package com.example.composetemplate.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dashboard.main.ui.Dashboard
import com.example.dashboard.useroverview.ui.UserOverview
import com.example.navigation.Routes
import com.example.navigation.animations.defaultEnterTransition
import com.example.navigation.animations.defaultExitTransition
import com.example.navigation.extensions.animatedComposable
import com.example.navigation.extensions.navigateToDestination
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Dashboard().route
    ) {
        composable(Routes.Dashboard().route) {
            Dashboard(navController::navigateToDestination)
        }

        composable(
            route = Routes.UserOverview().route + "/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backstackEntry ->
            UserOverview(backstackEntry.arguments?.getInt("userId") ?: -1)
        }
    }
}