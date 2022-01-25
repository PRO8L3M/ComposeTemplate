package com.example.navigation.extensions

import androidx.navigation.NavHostController
import com.example.navigation.Destination

fun NavHostController.navigateToDestination(destination: Destination) {
    navigate(route = destination.route)
}