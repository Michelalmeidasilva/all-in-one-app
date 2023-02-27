package com.michel.galileu.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface GroceryListCategoryDao {
    @Transaction
    @Query("SELECT * FROM grocerylistcategoryentity")
    suspend fun getAll(): List<GroceryListCategoryDao>


    @Query("SELECT * FROM grocerylistcategoryentity WHERE id = :id")
    suspend fun getById(id: Int): GroceryListCategoryDao


    @Insert
    suspend fun insert(category: GroceryListCategoryDao)


    @Update
    suspend fun update(category: GroceryListCategoryDao)


    @Delete
    suspend fun delete(category: GroceryListCategoryDao)
}