package com.michel.galileu.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.michel.galileu.models.recipe.RecipeModel


@Composable
fun RecipeScreen(navController: NavController, onRecipeDetailsClick: (String) -> Unit = {}) {
    val recipeData:  Array<RecipeModel> =  arrayOf(RecipeModel(steps = arrayOf("Cozinhar o feijão "), subtitle = "Top", title ="Strogonoff", ingredients = arrayOf("Arroz", "Feijão", "Carne") ))


    LazyColumn {
        recipeData.map { item { RecipeCard(it, onClick = { navController.navigate("recipe-details/${it}", null, null)}) } }
    }


}




@Composable
fun RecipeCard(recipeData: RecipeModel, onClick: () -> Unit ) {
    Row(modifier = Modifier.clickable {  onClick()}) {
        Surface(shape= MaterialTheme.shapes.medium, tonalElevation = 1.dp, modifier = Modifier.padding(all = 8.dp)) {

            Text("$1", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.width(8.dp))

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





