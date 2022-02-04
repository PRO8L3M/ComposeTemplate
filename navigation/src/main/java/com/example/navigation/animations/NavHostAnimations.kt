package com.example.navigation.animations

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

const val DEFAULT_FADE_TARGET_ALPHA = 0.5f

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<String>.defaultExitTransition() =
    fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.Start)

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<String>.defaultEnterTransition() =
    fadeIn() + slideIntoContainer(AnimatedContentScope.SlideDirection.End)

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<String>.defaultFadeOutTransition() =
    fadeOut(targetAlpha = DEFAULT_FADE_TARGET_ALPHA)

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<String>.defaultFadeInTransition() =
    fadeIn(initialAlpha = DEFAULT_FADE_TARGET_ALPHA)
