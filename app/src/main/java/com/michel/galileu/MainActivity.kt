package com.michel.galileu

import GalileuNavBar
import android.app.Application
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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.michel.galileu.navigation.GalileuNavHost
import com.michel.galileu.navigation.RecipesNavigation
import com.michel.galileu.navigation.bottomNavItems
import com.michel.galileu.ui.theme.GalileuTheme


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalileuApp(LocalContext.current.applicationContext as Application)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalileuApp(application: Application) {
    GalileuTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination;
        
        Scaffold(
            topBar = {
//                        if(currentScreen.route == RecipesNavigation.route){
//                            GalileuTopBar(currentScreen = currentScreen.name, onSearchValue = {})
//                        }
            },
            bottomBar = {
                GalileuNavBar(
                    currentScreen = currentDestination?.route,
                    onClickNavBar = { navController.navigate(it) })
            })
        { contentPadding ->
            GalileuNavHost(navController, modifier = Modifier.padding(contentPadding), application)
        }


    }
}




