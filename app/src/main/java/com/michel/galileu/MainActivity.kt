package com.michel.galileu

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.michel.galileu.ui.screens.RecipeDetailsScreen
import com.michel.galileu.ui.screens.RecipeData
import com.michel.galileu.ui.screens.RecipeScreen
import com.michel.galileu.ui.theme.GalileuTheme



class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            GalileuTheme {

                val navController = rememberNavController()

                val currentBackStack by navController.currentBackStackEntryAsState()


                NavHost(navController = navController, startDestination = "recipe") {
                    composable("recipe") { RecipeScreen(navController) }
                    composable("recipe-details/{recipe}", listOf(navArgument("recipe"){
                        type= NavType.ParcelableType(RecipeData::class.java)
                    })) { RecipeDetailsScreen(navController, it.arguments?.getParcelable("recipe", RecipeData::class.java))}
                }
            }
        }
    }
}




