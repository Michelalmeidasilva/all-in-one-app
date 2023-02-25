package com.michel.galileu.ui.screens.grocery

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GroceryListDetailsScreen(recypeType: String, modifier: Modifier, application: Application) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Details Grocery List")
    }
}