package com.michel.galileu.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroceryListEntity(
    @PrimaryKey(autoGenerate = true) val listId: Integer? = null,
    @ColumnInfo(name = "name_list") val name: String? = null,
    @ColumnInfo(name = "priceAmount") val priceAmount: String? = null,
    @ColumnInfo(name = "iconList") val iconList: String? = null,
    @ColumnInfo(name = "descriptionList") val descriptionList: String? = null,
    @Embedded val categoryList: GroceryListCategoryEntity? = null,
)