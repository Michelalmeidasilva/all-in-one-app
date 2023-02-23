package com.michel.galileu.ui.recipe

import android.annotation.SuppressLint

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.ui.viewmodel.RecipeViewModel

data class ItemList(val value: RecipeEntity, var isSelected: Boolean)

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun RecipeScreen(
    onAddRecipeClick: () -> Unit,
    onRecipeDetailsClick: (Int) -> Unit = {},
    recipeViewModel:  RecipeViewModel = viewModel(),
    ) {
    val recipesData = recipeViewModel.recipesData.collectAsState().value;
    val uiState = recipeViewModel.uiState.collectAsState().value;

    Box(modifier = Modifier.fillMaxSize()) {
            RecipeList(
                onDeleteItens = {
                    recipeViewModel.removeAllSelectedRecipes()
                },
                onUpdateRecipeList = { it, index ->
                    recipeViewModel.updateSelectedRecipe(it, index);

                    println(recipeViewModel.uiState.value!!.isAnyValueSelected)
                },
                onClearSelectedItens = {
                    recipeViewModel.clearSelectedRecipes();
                },
                recipesData,
                onRecipeDetailsClick = onRecipeDetailsClick,
                isSelectedValues = uiState.isAnyValueSelected
            )


        FloatingActionButton(
            onClick = onAddRecipeClick,
            modifier = Modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomEnd)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        }
    }
}







