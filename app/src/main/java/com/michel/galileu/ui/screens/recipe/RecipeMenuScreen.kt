package com.michel.galileu.ui.recipe

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.galileu.viewmodel.recipe.RecipeMenuViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box

@Composable
fun RecipeMenuScreen(modifier: Modifier = Modifier,
    application: Application,
    recipeMenuViewModel: RecipeMenuViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
//    val menus = recipeMenuViewModel.



    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}