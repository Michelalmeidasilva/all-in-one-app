package com.michel.galileu.ui.screens

import android.annotation.SuppressLint
import com.michel.galileu.R;

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.michel.galileu.ui.viewmodel.RecipeViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RecipeDetailsScreen(
    idString: String?,
    modifier: Modifier = Modifier,
    viewModel: RecipeViewModel = viewModel(),
    ){
    println(idString);

    viewModel.fetchRecipe(idString)
    val recipeState by viewModel.uiState.collectAsState()

    println(recipeState.toString());

    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize()){
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Column(modifier = Modifier.padding(all = 8.dp)) {
                Text(
                    text = recipeState.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = recipeState.subtitle)
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

            Column(modifier = Modifier.padding(all = 8.dp)) {
                Text("Ingredientes:", style= MaterialTheme.typography.titleLarge)
                recipeState.instructions.mapIndexed { index, it ->  Text(modifier = Modifier.padding(all= 4.dp), text="$index.  $it")}


                Text("Preparo:", modifier = Modifier.padding(all= 4.dp), style= MaterialTheme.typography.titleLarge)
                recipeState.ingredients.map {
                    Text(text=it)
                }
            }
        }
    }


}