package com.michel.galileu.ui.screens.home

import GalileuNavBar
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavHostController


@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(navController: NavHostController) {
    GalileuNavBar(
        onClickNavBar = { navController.navigate(it) })
}