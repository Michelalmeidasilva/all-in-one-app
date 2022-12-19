package com.michel.galileu.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michel.galileu.data.RecipesData
import com.michel.galileu.models.recipe.RecipeModel


@Composable
fun RecipeScreen( onRecipeDetailsClick: (String) -> Unit = {}) {
    val recipesData = RecipesData.recipes;


    LazyColumn {
        recipesData.map { item {  RecipeCard(recipeData = it, onClick = onRecipeDetailsClick) } }
    }


}


@Composable
fun RecipeCard(recipeData: RecipeModel, onClick: (String) -> Unit ) {
    ElevatedCard(modifier = Modifier.padding(4.dp).width(400.dp).clickable {  onClick(recipeData.id)}) {

        Row(modifier = Modifier.padding(all = 30.dp)){
            Text("$1", style = MaterialTheme.typography.titleLarge)

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





