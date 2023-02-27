package com.michel.galileu.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.michel.galileu.data.room.entities.ProductCategoryEntity

@Dao
interface ProductCategoryDao {
    @Transaction
    @Query("SELECT * FROM productcategoryentity")
    suspend fun getAll(): List<ProductCategoryEntity>


    @Query("SELECT * FROM productcategoryentity WHERE id = :id")
    suspend fun getById(id: Int): ProductCategoryEntity


    @Insert
    suspend fun insert(category: ProductCategoryEntity)


    @Update
    suspend fun update(category: ProductCategoryEntity)


    @Delete
    suspend fun delete(category: ProductCategoryEntity)
}