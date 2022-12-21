package com.michel.galileu.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michel.galileu.data.Data
import com.michel.galileu.models.recipe.RecipeModel


@Composable
fun RecipeScreen( onAddRecipeClick: () -> Unit,  onRecipeDetailsClick: (String) -> Unit = {}) {
    val recipesData = Data.recipes;

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier= Modifier.padding(all = 4.dp)) {
            recipesData.mapIndexed { index, it -> item { RecipeCard(recipeData = it, index = index, onClick = onRecipeDetailsClick)}    }
        }

        FloatingActionButton(onClick = onAddRecipeClick,
            modifier = Modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomEnd)) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")

        }
    }

}


@Composable
fun RecipeCard(recipeData: RecipeModel, index: Int, onClick: (String) -> Unit ) {
    ElevatedCard(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .height(90.dp)
        .clickable { onClick(recipeData.id) }) {
        Row(modifier = Modifier.padding(all = 30.dp)){
            Text("${index + 1} ", style = MaterialTheme.typography.titleLarge)
            Column(                   modifier = Modifier.padding(all = 4.dp)) {
                Text(
                    text = recipeData.title,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = recipeData.subtitle)
            }
        }
    }
}





