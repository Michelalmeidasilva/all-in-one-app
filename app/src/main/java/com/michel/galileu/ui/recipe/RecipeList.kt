package com.michel.galileu.ui.recipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow


@ExperimentalFoundationApi
@Composable
fun RecipeList(
    onDeleteItens: () -> Unit,
    onUpdateRecipeList: (value: ItemList, index: Int) -> Unit,
    onClearSelectedItens: () -> Unit,
    recipesData: List<ItemList>,
    onRecipeDetailsClick: (Int) -> Unit,
    isSelectedValues: Boolean,
    onChangeSearchText: (text: String) -> Unit,
    searchTextValue: String
) {

    Box(modifier = Modifier.fillMaxSize()) {
        TopBar(recipesData, onDeleteItens = {
            onDeleteItens()
        },  onClearSelectedItens, isEmptySelect = isSelectedValues, searchInput = SearchInput(text = searchTextValue, onClearText = { onChangeSearchText("")}, onChangeText = {
            onChangeSearchText(it)
        })  )
        LazyColumn(
            modifier = Modifier
                .absoluteOffset(y = 60.dp)
                .padding(all = 4.dp)
        ) {
            itemsIndexed(items = recipesData) { index, item ->
                RecipeCard(
                    recipeData = item,
                    onPressCheckBox = {
                        onUpdateRecipeList(item, index)
                    },
                    isSelectedValues = isSelectedValues
                ) {
                    onRecipeDetailsClick(item.value.id)
                }
            }
        }
    }
}
