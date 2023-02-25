package com.michel.galileu.ui.screens.grocery

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michel.galileu.models.GroceryListCategoryModel
import com.michel.galileu.models.GroceryListModel
import com.michel.galileu.ui.components.grocery.ListGroceryItems


object GroceryMock {
    val categories = arrayListOf("Mercado", "Padaria");
    val items: ArrayList<GroceryListModel> = arrayListOf(
        GroceryListModel(
            name = "Stok",
            priceAmount = "10.00",
            category = GroceryListCategoryModel(1, "Mercado")
        ),
        GroceryListModel(
            name = "Stok",
            priceAmount = "10.00",
            category = GroceryListCategoryModel(1, "Mercado")
        ),
        GroceryListModel(
            name = "Stok",
            priceAmount = "10.00",
            category = GroceryListCategoryModel(1, "Mercado")
        ),
        GroceryListModel(
            name = "Stok",
            priceAmount = "10.00",
            category = GroceryListCategoryModel(1, "Mercado")
        ),

        )
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GroceriesListsScreen(
    onClickGroceryList: (value: String) -> Unit,
    categories: ArrayList<String> = GroceryMock.categories,
    items: ArrayList<GroceryListModel> = GroceryMock.items
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(0.8f)
    ) {
        ListGroceryItems(
            onUpdateList = { null },
            data = items,
            onClickItem = onClickGroceryList,
            onChangeSearchText = {},
            searchTextValue = ""
        )

        Button(
            onClick = {},
            modifier = Modifier
                .padding(all = 8.dp)
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth(0.8f)
        ) {
            Text("Criar nova lista")
        }
    }

}

