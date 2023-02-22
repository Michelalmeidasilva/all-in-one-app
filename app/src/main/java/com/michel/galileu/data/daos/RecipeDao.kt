package com.michel.galileu.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.michel.galileu.data.entities.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipeentity")
    suspend fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipeentity WHERE id = :id")
    suspend fun getById(id: Int): RecipeEntity

    @Insert
    suspend fun insert(recipe: RecipeEntity)

    @Insert
    suspend fun insertAll(vararg recipes: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)
}