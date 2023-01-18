package com.michel.galileu.ui.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RecipeScheduleScreen(
    modifier: Modifier = Modifier,
    application: Application,

    ) {
    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {


            Spacer(modifier = Modifier.height(4.dp))


        }
    }
}