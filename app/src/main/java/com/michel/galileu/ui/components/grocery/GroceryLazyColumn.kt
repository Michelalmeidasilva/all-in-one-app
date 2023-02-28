package com.michel.galileu.ui.components.grocery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michel.galileu.data.entities.GroceryListEntity


@Composable
fun ListGroceryItems(
    onUpdateList: () -> Unit,
    data: List<GroceryListEntity>,
    onClickItem: (value: String) -> Unit,
    onChangeSearchText: () -> Unit,
    searchTextValue: String
) {
    Column(modifier = Modifier.padding(bottom = 60.dp, start = 16.dp, end = 16.dp)) {
        LazyColumn(
            modifier = Modifier
                .padding(all = 4.dp)
        ) {
            itemsIndexed(items = data) { index, item ->
                ItemCard(
                    item = item,
                    onClick = { onClickItem(item?.name!!) }
                )
            }
        }

    }
}


@Composable
fun ItemCard(item: GroceryListEntity, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)

    ) {
        Text(item?.name!!)
        Spacer(Modifier.width(8.dp))
        Text(item?.categoryList?.name!!)
        Spacer(Modifier.width(8.dp))
        Text(item.priceAmount)
        IconButton(onClick = onClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = "on click at list item",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}