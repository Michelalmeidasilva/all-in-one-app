package com.michel.galileu.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val productId: Integer,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "weight") val weight: Float,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "image") val image: Any,
    @Embedded val categoryProduct: ProductCategoryEntity
)



