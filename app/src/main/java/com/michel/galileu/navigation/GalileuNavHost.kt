package com.michel.galileu.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.michel.galileu.ui.screens.RecipeDetailsScreen
import com.michel.galileu.ui.screens.RecipeScreen

/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@Composable
fun GalileuNavHost (
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = RecipesNavigation.route,
        modifier = modifier
    ) {
        composable(route = RecipesNavigation.route) {
            RecipeScreen(
                onRecipeDetailsClick = { typeArg ->
                    navController.navigateToDetailsScreen(typeArg)
                }
            )
        }

        composable(
            route = RecipeDetailsNavigation.routeWithArgs,
            arguments = RecipeDetailsNavigation.arguments,
            deepLinks = RecipeDetailsNavigation.deepLinks
        ) { navBackStackEntry ->
            val accountType =
                navBackStackEntry.arguments?.getString(RecipeDetailsNavigation.typeArg)
            RecipeDetailsScreen(accountType)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

private fun NavHostController.navigateToDetailsScreen(accountType: String) {
    this.navigateSingleTopTo("${RecipeDetailsNavigation.route}/$accountType")
}