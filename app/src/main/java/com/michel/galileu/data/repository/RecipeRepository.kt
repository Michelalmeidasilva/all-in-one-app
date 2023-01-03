package com.michel.galileu.data.repository

import android.app.Application
import android.content.Context
import com.michel.galileu.data.daos.RecipeDao
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.data.room.DatabaseApp
import com.michel.galileu.data.room.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository(
    application: Application,
) {
    private val databaseApp: DatabaseApp = getDatabase(application)

    suspend fun insertRecipe(recipe: RecipeEntity) {
        withContext(Dispatchers.IO) {
            println("TO insert $recipe")
            databaseApp.recipeDao().insert(recipe)
        }
    }

    suspend fun getRecipeById(id: Int): RecipeEntity {
        var data: RecipeEntity;
        withContext(Dispatchers.IO) {
            data = databaseApp.recipeDao().getById(id);
        }
        return data;
    }

    suspend fun getRecipes(): List<RecipeEntity> {
        var data: List<RecipeEntity>;

        withContext(Dispatchers.IO) {
            data = databaseApp.recipeDao().getAll();
        }

        return data;
    }
}