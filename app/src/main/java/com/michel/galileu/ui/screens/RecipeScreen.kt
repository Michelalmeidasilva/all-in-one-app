package com.michel.galileu.ui.screens

import android.app.Application
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
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.data.repository.RecipeRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun RecipeScreen(
    onAddRecipeClick: () -> Unit, onRecipeDetailsClick: (Int) -> Unit = {},
    application: Application,
) {
    val repository = RecipeRepository(application);
    val recipesData = remember { mutableStateListOf<RecipeEntity>() }

    fun getData() {
        GlobalScope.launch {
            val recipes = repository.getRecipes();
            recipesData.addAll(recipes)
        }

    }

    LaunchedEffect(Unit) {
        getData()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.padding(all = 4.dp)) {
            recipesData.mapIndexed { index, it ->
                item {
                    RecipeCard(
                        recipeData = it,
                        index = index,
                        onClick = onRecipeDetailsClick
                    )
                }
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


@Composable
fun RecipeCard(recipeData: RecipeEntity, index: Int, onClick: (Int) -> Unit) {
    ElevatedCard(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .height(90.dp)
        .clickable { recipeData.id?.let { onClick(it) } }) {
        Row(modifier = Modifier.padding(all = 30.dp)) {
            Text("${index + 1} ", style = MaterialTheme.typography.titleLarge)
            Column(modifier = Modifier.padding(all = 4.dp)) {
                recipeData.title?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                recipeData.subtitle?.let { Text(text = it) }
            }
        }
    }
}





