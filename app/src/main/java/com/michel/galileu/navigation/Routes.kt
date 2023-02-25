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

package com.michel.galileu.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

/**
 * Contract for information needed on every Rally navigation destination
 */

interface NavigationSettings {
    val route: String
    val name: String
    val icon: ImageVector?
}

/**
 * Galileu app navigation destinations
 */
object HomeNavigation : NavigationSettings {
    override val route = "home"
    override val name = "Home"
    override val icon: ImageVector = Icons.Rounded.Home;
}

object RecipesNavigation : NavigationSettings {
    override val route = "recipes"
    override val name = "Receitas"
    override val icon: ImageVector = Icons.Rounded.AccountBox;
}

object RecipeAddNavigation : NavigationSettings {
    override val route = "recipe-add"
    override val icon: ImageVector = Icons.Rounded.Settings;
    override val name = "Adicionar Receita"
}

object RecipeDetailsNavigation : NavigationSettings {
    override val route = "details"
    override val name = "Receita"
    override val icon: ImageVector? = null;

    const val typeArg = "recipe_type"

    val routeWithArgs = "$route/{$typeArg}"

    val arguments = listOf(
        navArgument(typeArg) { type = NavType.StringType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "galileu://$route/{$typeArg}" }
    )
}


object RecipeMenu : NavigationSettings {
    override val route = "recipe-menu"
    override val name = "Cardápio"
    override val icon: ImageVector = Icons.Rounded.Menu;
}

object SettingsNavigation : NavigationSettings {
    override val route = "settings"
    override val icon: ImageVector = Icons.Rounded.Settings;
    override val name = "Configuração"
}


object GroceryList : NavigationSettings {
    override val route = "grocery-list"
    override val name = "Compras"
    override val icon: ImageVector = Icons.Rounded.ShoppingCart;
}


object GroceryDetailsNavigation : NavigationSettings {
    override val route = "grocery-details"
    override val name = "Grocery Details"
    override val icon: ImageVector = Icons.Rounded.Home;


    const val typeArg = "grocery_details_type"

    val routeWithArgs = "${route}/{$typeArg}"
    val arguments = listOf(
        navArgument(typeArg) { type = NavType.StringType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "galileu://${route}/{$typeArg}" }
    )
}


val bottomNavItems = listOf(RecipesNavigation, GroceryList, RecipeMenu, SettingsNavigation)

