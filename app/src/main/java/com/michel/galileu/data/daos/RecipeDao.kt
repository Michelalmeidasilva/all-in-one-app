package com.michel.galileu.data.daos

import androidx.room.*
import com.michel.galileu.data.room.entities.RecipeEntity

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

    @Update
    suspend fun update(recipe: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)
}