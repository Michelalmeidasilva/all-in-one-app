package com.michel.galileu.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroceryListCategoryEntity(
    @PrimaryKey(autoGenerate = true) val listCategoryId: Int,
    @ColumnInfo(name = "name") val name: String? = ""
)
