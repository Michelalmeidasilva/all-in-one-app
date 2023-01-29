package com.michel.galileu.ui.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.data.repository.RecipeRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


data class ItemList(val value: RecipeEntity, var isSelected: Boolean)


@SuppressLint("UnrememberedMutableState")
@OptIn(DelicateCoroutinesApi::class, ExperimentalFoundationApi::class)
@Composable
fun RecipeScreen(
    onAddRecipeClick: () -> Unit, onRecipeDetailsClick: (Int) -> Unit = {},
    application: Application,
) {
    val repository = RecipeRepository(application);
    val recipesData = remember { mutableStateListOf<ItemList>() }

    fun getData() {
        GlobalScope.launch {
            val recipes = repository.getRecipes();
            recipesData.addAll(recipes.map {
                ItemList(it, isSelected = false);
            })
        }
    }

    LaunchedEffect(Unit) {
        getData()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        PressionableList(onDeleteItens = {
            recipesData.removeAll(recipesData.filter { it.isSelected })
        }, onChangeRecipesData = { it, index ->
            if (recipesData[index].isSelected) {
                recipesData[index] = it.copy(isSelected = false)

            } else {
                recipesData[index] = it.copy(isSelected = true)

            }
        }, recipesData, onRecipeDetailsClick, onClearSelectedItens = {
            for (i in 0 until recipesData.size) {
                recipesData[i] = recipesData[i].copy(isSelected = false)
            }
//            recipesData.forEach { it.isSelected = false }
        })

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
fun ActionsBar(
    onDeleteItens: () -> Unit,
    onClearSelectedItens: () -> Unit,
    recipesData: MutableList<ItemList>
) {

    Card(
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        modifier = Modifier.height(56.dp)
    ) {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {

            if (recipesData.any { it.isSelected }) {
                IconButton(onClick = { onClearSelectedItens() }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "BACK",
                        modifier = Modifier
                            .size(24.dp)

                    )
                }

                IconButton(
                    modifier = Modifier.padding(start = 300.dp),
                    onClick = { onDeleteItens() }) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "DELETE",
                        modifier = Modifier
                            .size(24.dp)

                    )
                }
            } else {
                Text("Whatever", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp))

            }

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(onClick = {
                    state.value =
                        TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                }) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
    )
}


fun hasValueSelected(selectedItens: MutableList<ItemList>): Boolean {
    return selectedItens.any { it.isSelected }
}

@Composable
fun TopBar(
    recipesData: MutableList<ItemList>,
    onDeleteItens: () -> Unit,
    searchTextValue: MutableState<TextFieldValue>,
    onClearSelectedItens: () -> Unit
) {
    ActionsBar(onDeleteItens, onClearSelectedItens, recipesData)
}


@ExperimentalFoundationApi
@Composable
fun PressionableList(
    onDeleteItens: () -> Unit,
    onChangeRecipesData: (value: ItemList, index: Int) -> Unit,
    recipesData: MutableList<ItemList>,
    onRecipeDetailsClick: (Int) -> Unit,
    onClearSelectedItens: () -> Unit
) {
    val searchTextValue = remember { mutableStateOf(TextFieldValue("")) }
    val anyValueSelected = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        TopBar(recipesData, onDeleteItens, searchTextValue, onClearSelectedItens)

        recipesData.let {
            LazyColumn(
                modifier = Modifier
                    .absoluteOffset(y = 60.dp)
                    .padding(all = 4.dp),
                state = rememberLazyListState()
            ) {
                items(recipesData.size) {
                    val item = recipesData[it]

                    RecipeCard(recipeData = item.value,
                        isSelected = item.isSelected,
                        anyValueSelected = anyValueSelected,
                        onPressCheckBox = {
                            onChangeRecipesData(item, it)
                            anyValueSelected.value = hasValueSelected(recipesData)
                        },
                        onRecipeDetailsClick = {
                            onRecipeDetailsClick(item.value.id)
                        })

                }
            }
        }


    }
}


@ExperimentalFoundationApi
@SuppressLint("UnrememberedMutableState")
@Composable
fun RecipeCard(
    recipeData: RecipeEntity,
    onPressCheckBox: () -> Unit,
    isSelected: Boolean,
    anyValueSelected: MutableState<Boolean>,
    onRecipeDetailsClick: (Int) -> Unit
) {

    Row() {
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = if (isSelected) Color.LightGray else MaterialTheme.colorScheme.background,
            ),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(75.dp)
                .combinedClickable(onClick = {
                    if (anyValueSelected.value) {
                        onPressCheckBox()
                    } else {
                        onRecipeDetailsClick(recipeData.id)
                    }
                }, onLongClick = {
                    onPressCheckBox();

                })

        ) {

            Row(
                modifier = Modifier.padding(all = 4.dp)

            ) {
                Column(modifier = Modifier.padding(all = 4.dp)) {
                    Text(
                        text = recipeData.title, color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    recipeData.subtitle?.let { Text(text = it) }
                }
            }
        }
    }

}





