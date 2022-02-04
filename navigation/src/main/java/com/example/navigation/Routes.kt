package com.example.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

object Routes {

    data class Dashboard(
        override val route: String = "dashboard",
        override val args: List<NamedNavArgument> = emptyList(),
        override val deepLinks: List<NavDeepLink> = emptyList()
    ) : Destination

    data class UserOverview(
        override val route: String = "useroverview",
        override val args: List<NamedNavArgument> = emptyList(),
        override val deepLinks: List<NavDeepLink> = emptyList()
    ) : Destination
}

