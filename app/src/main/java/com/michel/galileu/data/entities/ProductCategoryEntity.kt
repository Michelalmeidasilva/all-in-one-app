package com.michel.galileu.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductCategoryEntity(
    @PrimaryKey(autoGenerate = true) val productCategoryId: Int,
    @ColumnInfo(name = "name") val name: String
)