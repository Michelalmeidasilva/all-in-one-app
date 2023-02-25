package com.michel.galileu.ui.screens.product

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProductDetailsScreen(
    recypeType: Int?,
    modifier: Modifier,
    application: Application
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Details Product")
    }
}
