package com.michel.galileu

import GalileuNavBar
import GalileuTopBar
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.michel.galileu.navigation.GalileuNavHost
import com.michel.galileu.navigation.RecipesNavigation
import com.michel.galileu.navigation.bottomNavItems
import com.michel.galileu.ui.theme.GalileuTheme
import com.michel.galileu.ui.viewmodel.RecipeViewModel


class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
          GalileuApp()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun GalileuApp(
){
    GalileuTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination;

        val currentScreen = bottomNavItems.find { it.route == currentDestination?.route } ?: RecipesNavigation


        Scaffold(
            topBar = {
//                        if(currentScreen.route == RecipesNavigation.route){
//                            GalileuTopBar(currentScreen = currentScreen.name, onSearchValue = {})
//                        }
                     },
            bottomBar = {
                        GalileuNavBar( currentScreen= currentDestination?.route, onClickNavBar = { navController.navigate(it) })
                    })
        { contentPadding ->
            GalileuNavHost(navController, modifier = Modifier.padding(contentPadding))
        }


    }
}




