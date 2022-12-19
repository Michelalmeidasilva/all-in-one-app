package com.michel.galileu.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michel.galileu.data.RecipesData

@Composable
fun RecipeDetailsScreen(recypeType: String?){
    val recipe = remember { RecipesData.getRecipe(recipeId = recypeType) }


    Column(modifier = Modifier.padding(10.dp)) {

            Text("$1", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.padding(all = 4.dp)) {
                Text(
                    text = recipe.title,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = recipe.subtitle)
            }

            Column(modifier = Modifier.padding(all=4.dp)){
                recipe.instructions.map {
                    Text(text=it)
                }
            }

            Column(modifier = Modifier.padding(all=4.dp)){
                recipe.ingredients.map {
                    Text(text=it)
                }
            }
    }
}