package com.michel.galileu.ui.screens.recipe

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
import com.michel.galileu.data.room.entities.RecipeEntity
import com.michel.galileu.ui.components.recipe.RecipeList
import com.michel.galileu.ui.viewmodel.recipe.RecipeViewModel

data class ItemList(val value: RecipeEntity, var isSelected: Boolean)

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun RecipeScreen(
    onAddRecipeClick: () -> Unit,
    onRecipeDetailsClick: (Int) -> Unit = {},
    recipeViewModel: RecipeViewModel = viewModel(),
) {
    val recipesData = recipeViewModel.filteredRecipes.collectAsState(emptyList()).value;
    val uiState = recipeViewModel.uiState.collectAsState().value;
    val searchText = recipeViewModel.query.collectAsState();

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
            isSelectedValues = uiState.isAnyValueSelected,
            onChangeSearchText = { value ->
                recipeViewModel.onChangeQuery(value)
            },
            searchTextValue =
            searchText.value.text


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







