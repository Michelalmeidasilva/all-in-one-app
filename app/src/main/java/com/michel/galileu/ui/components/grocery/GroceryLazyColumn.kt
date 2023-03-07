package com.michel.galileu.ui.components.grocery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.michel.galileu.data.entities.GroceryListEntity


@Composable
fun ListGroceryItems(
    onUpdateList: () -> Unit,
    data: List<GroceryListEntity>,
    onClickItem: (value: String) -> Unit,
    onChangeSearchText: () -> Unit,
    searchTextValue: String
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        LazyColumn {
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
            .clickable {
                onClick()
            }

    ) {
        Row(modifier = Modifier.align(Alignment.CenterVertically)) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically),
                imageVector = Icons.Rounded.Info,
                contentDescription = "on click at list item",
                tint = MaterialTheme.colorScheme.primary,
            )

            Column(modifier = Modifier.padding(start = 16.dp)) {

                item.name?.let {
                    Text(
                        it,
                        style = TextStyle.Default.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }

                item.descriptionList?.let {
                    Text(
                        it,
                        style = TextStyle.Default.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Thin
                        )
                    )
                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            IconButton(
                onClick = onClick, modifier = Modifier
                    .align(Alignment.CenterEnd)

            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "on click at list item",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

    }
}