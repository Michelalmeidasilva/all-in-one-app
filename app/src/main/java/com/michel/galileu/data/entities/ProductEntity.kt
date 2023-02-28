package com.michel.galileu.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val productId: Integer,
    @ColumnInfo(name = "name_product") val name: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "weight") val weight: Float,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "image_path") val image_path: String,
    @Embedded val categoryProduct: ProductCategoryEntity
)



