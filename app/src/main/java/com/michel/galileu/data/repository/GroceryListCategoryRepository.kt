package com.michel.galileu.data.repository

import android.app.Application
import com.michel.galileu.data.entities.GroceryListCategoryEntity
import com.michel.galileu.data.room.DatabaseApp
import com.michel.galileu.data.room.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GroceryListCategoryRepository(application: Application) {
    private val databaseApp: DatabaseApp = getDatabase(application)


    suspend fun insertCategory(value: GroceryListCategoryEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.groceryListCategoryDao().insert(value)
        }
    }

    suspend fun getCategoryById(id: Int): GroceryListCategoryEntity {
        var data: GroceryListCategoryEntity;
        withContext(Dispatchers.IO) {
            data = databaseApp.groceryListCategoryDao().getById(id);
        }
        return data;
    }

    suspend fun getCategories(): List<GroceryListCategoryEntity> {
        var data: List<GroceryListCategoryEntity>;

        withContext(Dispatchers.IO) {
            data = databaseApp.groceryListCategoryDao().getAll();
        }

        return data;
    }


    suspend fun removeCategory(values: List<GroceryListCategoryEntity>) {
        withContext(Dispatchers.IO) {
            values.forEach {
                databaseApp.groceryListCategoryDao().delete(it)
            }
        }
    }

    suspend fun updateCategory(value: GroceryListCategoryEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.groceryListCategoryDao().update(value)
        }
    }
}