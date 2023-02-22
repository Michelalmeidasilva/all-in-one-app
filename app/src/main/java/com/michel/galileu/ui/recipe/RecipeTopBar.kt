package com.michel.galileu.ui.recipe

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


class SearchInput ( val text: TextFieldValue, val onChangeText: (value: TextFieldValue) -> Unit,
    val onClearText: () -> Unit
    )

@Composable
fun TopBar(
    recipesData: List<ItemList>,
    onDeleteItens: () -> Unit,
    onClearSelectedItens: () -> Unit,
    isEmptySelect: Boolean,
    searchInput: SearchInput
) {
    if(isEmptySelect){
        ActionsBar(onDeleteItens, onClearSelectedItens, recipesData)
    } else {
        SearchBar(searchInput)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchInput: SearchInput) {
    TextField(
        value = searchInput.text,
        onValueChange = { value -> searchInput.onChangeText(value)},
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
            if (searchInput.text != TextFieldValue("")) {
                IconButton(onClick = {
                    searchInput.onClearText()
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
        }
    )
}





@Composable
fun ActionsBar(
    onDeleteItens: () -> Unit,
    onClearSelectedItens: () -> Unit,
    recipesData: List<ItemList>
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
