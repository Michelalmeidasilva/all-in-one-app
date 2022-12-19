package com.michel.galileu

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.michel.galileu.navigation.GalileuNavHost
import com.michel.galileu.ui.theme.GalileuTheme



class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            GalileuTheme {
                val navController = rememberNavController()

                GalileuNavHost(navController)

            }
        }
    }
}




