package com.michel.galileu.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.michel.galileu.data.entities.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipeentity")
    fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM recipeentity WHERE id = :id")
    fun getById(id: Int): RecipeEntity

    @Insert
    fun insert(recipe: RecipeEntity)

    @Insert
    fun insertAll(vararg recipes: RecipeEntity)

    @Delete
    fun delete(user: RecipeEntity)
}