package com.michel.galileu.ui.screens

import com.michel.galileu.R;

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.michel.galileu.data.Data


@Composable
fun RecipeDetailsScreen(recypeType: String?){
    val recipe = remember { Data.getRecipe(recipeId = recypeType) }
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    Column(modifier = Modifier.padding(10.dp).scrollable(state= scrollState, orientation = Orientation.Vertical)) {
        Column(modifier = Modifier.padding(all = 4.dp)) {
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = recipe.subtitle)
        }


        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(color = Color.Gray), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.meal),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text("Ingredientes:", style= MaterialTheme.typography.titleLarge)
        Column(modifier = Modifier.padding(all=8.dp)){
            recipe.instructions.mapIndexed { index, it ->  Text(text="$index.  $it")}
        }


        Text("Preparo:", style= MaterialTheme.typography.titleLarge)
        Column(modifier = Modifier.padding(all=8.dp)){
            recipe.ingredients.map {
                Text(text=it)
            }
        }
    }

}