package com.michel.galileu.ui.recipe

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.galileu.viewmodel.recipe.RecipeMenuViewModel

@Composable
fun RecipeMenuScreen(
    modifier: Modifier = Modifier,
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