package com.michel.galileu.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroceryListEntity(
    @PrimaryKey(autoGenerate = true) val listId: Integer? = null,
    @ColumnInfo(name = "") val name: String,
    @ColumnInfo(name = "") val priceAmount: String,
    @ColumnInfo(name = "") val icon: String? = null,
    @Embedded val categoryList: GroceryListCategoryEntity,
    @ColumnInfo(name = "") val products: ArrayList<ProductEntity>? = null
)