package com.example.navigation.extensions

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.example.navigation.Destination
import com.google.accompanist.navigation.animation.composable

@ExperimentalAnimationApi
fun NavGraphBuilder.animatedComposable(
    destination: Destination,
    content: @Composable AnimatedVisibilityScope.(entry: NavBackStackEntry) -> Unit,
) {
    composable(
        route = destination.route,
        arguments = destination.args,
        deepLinks = destination.deepLinks,
        content = content
    )
}