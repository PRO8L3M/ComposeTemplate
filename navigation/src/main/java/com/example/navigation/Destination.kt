package com.example.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

interface Destination {
    val route: String
    val args: List<NamedNavArgument>
    val deepLinks: List<NavDeepLink>
}