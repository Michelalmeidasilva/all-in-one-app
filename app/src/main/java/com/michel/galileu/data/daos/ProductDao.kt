package com.michel.galileu.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.michel.galileu.data.room.entities.ProductEntity

@Dao
interface ProductDao {


    @Transaction
    @Query("SELECT * FROM productentity")
    suspend fun getAll(): List<ProductEntity>


    @Query("SELECT * FROM productentity WHERE id = :id")
    suspend fun getById(id: Int): ProductEntity


    @Insert
    suspend fun insert(productEntity: ProductEntity)


    @Update
    suspend fun update(productEntity: ProductEntity)


    @Delete
    suspend fun delete(productEntity: ProductEntity)


}