package com.michel.galileu.models

data class ProductModel(
    val id: Integer,
    val name: String,
    val quantity: Int,
    val weight: Float,
    val price: Float,
    val image: Any,
    val productCategory: ProductCategoryModel
)