package com.michel.galileu.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.michel.galileu.data.entities.GroceryListEntity
import com.michel.galileu.data.entities.GroceryListWithProductsEntity


@Dao
interface GroceryListDao {
    @Transaction
    @Query("SELECT * FROM grocerylistentity")
    suspend fun getAll(): List<GroceryListEntity>

    @Query("SELECT * FROM grocerylistentity WHERE listId = :listId")
    suspend fun getById(listId: Int): GroceryListEntity

    @Query("SELECT * FROM grocerylistentity")
    suspend fun getGroceryListWithProducts(): List<GroceryListWithProductsEntity>

    @Query("SELECT * FROM grocerylistentity WHERE listId = :listId")
    suspend fun getGroceryListWithProductsById(listId: Int): List<GroceryListWithProductsEntity>

    @Insert
    suspend fun insert(groceryList: GroceryListEntity)

    @Update
    suspend fun update(groceryList: GroceryListEntity)

    @Delete
    suspend fun delete(groceryList: GroceryListEntity)
}