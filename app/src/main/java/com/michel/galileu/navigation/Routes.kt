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
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
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
 * Rally app navigation destinations
 */
object HomeNavigation : NavigationSettings {
    override val route = "home"
    override val name = "Home"
    override val icon: ImageVector = Icons.Rounded.Home;
}


/**
 * Rally app navigation destinations
 */
object RecipesNavigation : NavigationSettings {
    override val route = "recipes"
    override val name = "Receitas"
    override val icon: ImageVector = Icons.Rounded.Home;
}

object RecipeSchedule : NavigationSettings {
    override val route = "recipeSchedule"
    override val name = "Agenda"
    override val icon: ImageVector = Icons.Rounded.AccountBox;
}

object SettingsNavigation : NavigationSettings {
    override val route = "settings"
    override val icon: ImageVector = Icons.Rounded.Settings;
    override val name = "Config"
}

object RecipeAddNavigation : NavigationSettings {
    override val route = "recipe-add"
    override val icon: ImageVector = Icons.Rounded.Settings;
    override val name = "Recipe"
}

object RecipeDetailsNavigation : NavigationSettings {
    // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
    // part of the RallyTabRow selection
    override val route = "details"
    override val name = "Details"
    override val icon: ImageVector? = null;

    const val typeArg = "recipe_type"

    val routeWithArgs = "$route/{$typeArg}"
    val arguments = listOf(
        navArgument(typeArg) { type = NavType.StringType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "rally://$route/{$typeArg}" }
    )
}


val bottomNavItems = listOf(RecipesNavigation, RecipeSchedule, SettingsNavigation)

