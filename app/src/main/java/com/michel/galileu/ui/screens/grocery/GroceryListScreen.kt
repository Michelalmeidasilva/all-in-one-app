package com.michel.galileu.ui.screens.grocery

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michel.galileu.ui.components.grocery.ListGroceryItems


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GroceryListScreen(
    onClickGroceryList: (value: String) -> Unit,
    categories: ArrayList<String> = arrayListOf("Mercado", "Padaria"),
    items: ArrayList<ItemParam> = arrayListOf(
        ItemParam(
            name = "Stok",
            priceAmount = "10.00",
            products = 10,
            category = "Mercado"
        ),
        ItemParam(
            name = "Stok",
            priceAmount = "10.00",
            products = 10,
            category = "Mercado"
        ),
        ItemParam(
            name = "Stok",
            priceAmount = "10.00",
            products = 10,
            category = "Mercado"
        ),
        ItemParam(
            name = "Stok",
            priceAmount = "10.00",
            products = 10,
            category = "Mercado"
        ),
        ItemParam(
            name = "Padaria",
            priceAmount = "10.00",
            products = 10,
            category = "Padaria"
        ),
        ItemParam(
            name = "Padaria",
            priceAmount = "10.00",
            products = 10,
            category = "Padaria"
        ),
        ItemParam(
            name = "Padaria",
            priceAmount = "10.00",
            products = 10,
            category = "Padaria"
        ),
        ItemParam(
            name = "Padaria",
            priceAmount = "10.00",
            products = 10,
            category = "Padaria"
        ),
        ItemParam(
            name = "Padaria",
            priceAmount = "10.00",
            products = 10,
            category = "Padaria"
        )
    )
) {
    val scrollState = rememberScrollState()

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

class ItemParam(
    val name: String,
    val priceAmount: String,
    val products: Int,
    val category: String
)


