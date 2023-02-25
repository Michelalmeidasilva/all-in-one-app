package com.michel.galileu.ui.components.recipe

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.michel.galileu.ui.components.textfield.SearchActions
import com.michel.galileu.ui.components.textfield.SearchBar
import com.michel.galileu.ui.screens.recipe.ItemList


@Composable
fun RecipeTopBar(
    recipesData: List<ItemList>,
    onDeleteItens: () -> Unit,
    onClearSelectedItens: () -> Unit,
    isEmptySelect: Boolean,
    searchActions: SearchActions
) {
    if (isEmptySelect) {
        ActionsBar(onDeleteItens, onClearSelectedItens, recipesData)
    } else {
        SearchBar(searchActions)
    }
}


@Composable
fun ActionsBar(
    onDeleteItens: () -> Unit,
    onClearSelectedItens: () -> Unit,
    recipesData: List<ItemList>
) {
    Card(
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
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
                Text(
                    "Whatever", modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )
            }
        }
    }
}
