package com.example.dashboard.useroverview.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UserOverview(userId: Int) {
    //todo pass User as argument
    UserOverviewPage(id = userId.toLong())
}

@Composable
fun UserOverviewPage(id: Long) {
    Text(text = "User id: $id")
}

@Preview
@Composable
fun UserOverviewPreview() {
    UserOverviewPage(id = 1)
}