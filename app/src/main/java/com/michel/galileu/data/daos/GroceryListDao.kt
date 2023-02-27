package com.michel.galileu.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.michel.galileu.data.room.entities.GroceryListEntity
import com.michel.galileu.data.room.entities.GroceryListWithProductsEntity


@Dao
interface GroceryListDao {
    @Transaction
    @Query("SELECT * FROM grocerylistentity")
    suspend fun getAll(): List<GroceryListEntity>


    @Query("SELECT * FROM grocerylistentity WHERE id = :id")
    suspend fun getById(id: Int): GroceryListEntity

    @Query("SELECT * FROM grocerylistentity")
    suspend fun getGroceryListWithProducts(id: Int): List<GroceryListWithProductsEntity>


    @Query("SELECT * FROM grocerylistentity WHERE listId = :id")
    suspend fun getGroceryListWithProductsById(id: Int): List<GroceryListWithProductsEntity>


    @Insert
    suspend fun insert(groceryList: GroceryListEntity)


    @Update
    suspend fun update(groceryList: GroceryListEntity)


    @Delete
    suspend fun delete(groceryList: GroceryListEntity)

}