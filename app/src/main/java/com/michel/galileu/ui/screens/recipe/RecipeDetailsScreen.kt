package com.michel.galileu.ui.screens.recipe

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.galileu.utils.IOManager
import com.michel.galileu.utils.changeListType
import com.michel.galileu.viewmodel.recipe.RecipeDetailsViewModel

@Composable
fun ListItems(items: List<String?>?, listType: ListType, title: String) {
    if (items?.isEmpty() == false) {
        Text(title, style = MaterialTheme.typography.titleMedium)

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

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RecipeDetailsScreen(
    idString: Int,
    modifier: Modifier = Modifier,
    application: Application,
    viewModel: RecipeDetailsViewModel = viewModel()

) {
    LaunchedEffect(Unit) {
        viewModel.fetchRecipe(idString)
    }

    val recipeState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val editMode = viewModel.editMode.collectAsState()




    println(recipeState)


    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            AboutRecipe(
                title = recipeState.title,
                subtitle = recipeState.description,
                imageUrl = recipeState.imagePath,
                application
            )

            Spacer(modifier = Modifier.height(4.dp))

            Column(modifier = Modifier.padding(all = 8.dp)) {
                ListItems(
                    items = recipeState.instructions,
                    ListType.ORDERED_LIST,
                    "Ingredientes"
                )
                ListItems(
                    items = recipeState.ingredients,
                    ListType.NUMBERED_LIST,
                    "Preparo"
                )
            }


//            OutlinedButton(modifier = Modifier
//                .padding(horizontal = 10.dp)
//                .background(Color.Transparent)
//                .height(50.dp),
//                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
//                shape = RoundedCornerShape(80),
//                onClick = { viewModel.editMode.value = !editMode.value }) {
//
//                Icon(
//                    modifier = Modifier.size(18.dp),
//                    imageVector = Icons.Filled.Edit,
//                    tint = MaterialTheme.colorScheme.primary,
//                    contentDescription = "Add"
//                )
//                Text("Editar")
//            }
        }

//        if(editMode.value){
//            FloatingActionButton(
//                containerColor = Color.White,
//                onClick = {
//                    viewModel.updateRecipe(RecipeEntity(recipeState.id, recipeState.title, recipeState.subtitle, recipeState.instructions,recipeState.ingredients,  recipeState.imagePath))
//                    viewModel.editMode.value = false
//                },
//                modifier = Modifier
//                    .padding(all = 4.dp)
//                    .align(alignment = Alignment.BottomEnd)
//
//            ) {
//                Icon(
//                    imageVector = Icons.Outlined.CheckCircle,
//                    contentDescription = "Finish",
//                    modifier = Modifier.size(32.dp),
//                    tint = Color.Green
//                )
//            }
//        }
    }
}