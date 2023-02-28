package com.michel.galileu.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation


@Entity(primaryKeys = ["listId", "productId"])
data class GroceryListProductsCrossRef(
    val listId: Integer,
    val productId: Integer
)


@Entity
data class GroceryListWithProductsEntity(
    @Embedded
    val list: GroceryListEntity,
    @Relation(
        parentColumn = "listId",
        entityColumn = "productId",
        associateBy = Junction(GroceryListProductsCrossRef::class)

    )
    val products: List<ProductEntity>

)


