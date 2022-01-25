package com.example.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

object Routes {

    object Dashboard : Destination {
        override val route: String = "dashboard"
        override val args: List<NamedNavArgument> = emptyList()
        override val deepLinks: List<NavDeepLink> = emptyList()
    }

    object UserOverview : Destination {
        override val route: String = "useroverview"
        override val args: List<NamedNavArgument> = emptyList()
        override val deepLinks: List<NavDeepLink> = emptyList()
    }
}

