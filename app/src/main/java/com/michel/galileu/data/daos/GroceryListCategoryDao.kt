package com.michel.galileu.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.michel.galileu.data.room.entities.GroceryListCategoryEntity

@Dao
interface GroceryListCategoryDao {
    @Transaction
    @Query("SELECT * FROM grocerylistcategoryentity")
    suspend fun getAll(): List<GroceryListCategoryEntity>


    @Query("SELECT * FROM grocerylistcategoryentity WHERE id = :id")
    suspend fun getById(id: Int): GroceryListCategoryEntity


    @Insert
    suspend fun insert(category: GroceryListCategoryEntity)


    @Update
    suspend fun update(category: GroceryListCategoryEntity)


    @Delete
    suspend fun delete(category: GroceryListCategoryEntity)
}