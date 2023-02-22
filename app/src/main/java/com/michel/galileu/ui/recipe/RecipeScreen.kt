package com.michel.galileu.ui.recipe

import android.annotation.SuppressLint

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    Box(modifier = Modifier.fillMaxSize()) {
        recipeViewModel.recipesData.value?.let {
            PressionableList(
                onDeleteItens = {
                    recipeViewModel.removeAllSelectedRecipes()
                },
                onUpdateRecipeList = { it, index ->
                    recipeViewModel.updateSelectedRecipe(it, index);

                    println(recipeViewModel.uiState.value!!.anyValueSelected)
                },
                onClearSelectedItens = {
                    recipeViewModel.clearSelectedRecipes();
                },
                recipeViewModel.recipesData,
                onRecipeDetailsClick = onRecipeDetailsClick,
                isSelectedValues = {
                    recipeViewModel.uiState.value!!.anyValueSelected
                }
            ) { index ->
                recipeViewModel.isRecipeSelected(index)
            }
        }

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







