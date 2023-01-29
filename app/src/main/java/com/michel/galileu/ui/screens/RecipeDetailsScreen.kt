package com.michel.galileu.ui.screens

import android.annotation.SuppressLint
import android.app.Application

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.michel.galileu.ui.viewmodel.RecipeViewModel
import com.michel.galileu.utils.IOManager
import com.michel.galileu.utils.changeListType

@Composable
fun ListItems(items: List<String>?, listType: ListType, title: String) {
    if (items?.isEmpty() == false) {
        Text(title, style = MaterialTheme.typography.titleLarge)

        items.mapIndexed { index, it ->
            Text(
                modifier = Modifier.padding(
                    all = 4.dp
                ), text = "${changeListType(listType, index + 1)} $it"
            )
        }
    }

}

@Composable
fun AboutRecipe(title: String?, subtitle: String?, imageUrl: String?, application: Application) {
    val manager = IOManager();
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(color = Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (imageUrl?.isBlank() == false) {
            manager.getImage(application, imageUrl)?.let {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }
    }

    Column(modifier = Modifier.padding(all = 8.dp)) {
        title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        subtitle?.let { Text(text = it) }
    }
}


/**
 * Elementos:
 *
 * 1. Componente na header com Lixeira.
 *
 * Implementar um componente de gesture de listagem.
 * - Ao pressionar vai selecionar o item.
 * - Ele poderá excluir.
 *
 * - Ao pressionar algum item, vai mudar pra listagem de checkbox
 *
 */


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RecipeDetailsScreen(
    idString: Int,
    modifier: Modifier = Modifier,
    application: Application,
    viewModel: RecipeViewModel = viewModel()

) {
    LaunchedEffect(Unit) {
        viewModel.fetchRecipe(idString)
    }

    val recipeState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            AboutRecipe(
                title = recipeState.title,
                subtitle = recipeState.subtitle,
                imageUrl = recipeState.imagePath,
                application
            )

            Spacer(modifier = Modifier.height(4.dp))

            Column(modifier = Modifier.padding(all = 8.dp)) {
                ListItems(
                    items = recipeState.instructions as List<String>?,
                    ListType.ORDERED_LIST,
                    "Ingredientes"
                )
                ListItems(
                    items = recipeState.ingredients as List<String>?,
                    ListType.NUMBERED_LIST,
                    "Preparo"
                )
            }
        }
    }
}