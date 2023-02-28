package com.michel.galileu.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroceryListEntity(
    @PrimaryKey(autoGenerate = true) val listId: Integer? = null,
    @ColumnInfo(name = "name_list") val name: String? = null,
    @ColumnInfo(name = "priceAmount") val priceAmount: String,
    @ColumnInfo(name = "icon") val icon: String? = null,
    @Embedded val categoryList: GroceryListCategoryEntity,
)