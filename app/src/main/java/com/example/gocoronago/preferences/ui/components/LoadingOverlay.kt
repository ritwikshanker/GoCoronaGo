package com.example.gocoronago.preferences.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable

@ExperimentalAnimationApi
@Composable
fun LoadingOverlay(
    loading: Boolean
) {
    AnimatedVisibility(visible = loading) {
        CircularProgressIndicator()
    }
}