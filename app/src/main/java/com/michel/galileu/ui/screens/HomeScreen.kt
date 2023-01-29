package com.michel.galileu.ui.screens

import GalileuNavBar
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(navController: NavHostController) {
    GalileuNavBar(
        onClickNavBar = { navController.navigate(it) })
}