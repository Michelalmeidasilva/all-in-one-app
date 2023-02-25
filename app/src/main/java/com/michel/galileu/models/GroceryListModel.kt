package com.michel.galileu.models

class GroceryListModel(
    val id: Integer? = null,
    val name: String,
    val priceAmount: String,
    val category: GroceryListCategoryModel? = null,
    val products: ArrayList<ProductModel>? = null,
    val icon: String? = null,
)