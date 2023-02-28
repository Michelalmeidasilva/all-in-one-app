package com.michel.galileu.data.repository

import android.app.Application
import com.michel.galileu.data.entities.GroceryListEntity
import com.michel.galileu.data.room.DatabaseApp
import com.michel.galileu.data.room.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GroceryListRepository(
    application: Application,
) {
    private val databaseApp: DatabaseApp = getDatabase(application)


    suspend fun insertCategory(value: GroceryListEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.groceryListDao().insert(value)
        }
    }

    suspend fun getCategoryById(id: Int): GroceryListEntity {
        var data: GroceryListEntity;
        withContext(Dispatchers.IO) {
            data = databaseApp.groceryListDao().getById(id);
        }
        return data;
    }

    suspend fun getCategories(): List<GroceryListEntity> {
        var data: List<GroceryListEntity>;

        withContext(Dispatchers.IO) {
            data = databaseApp.groceryListDao().getAll();
        }

        return data;
    }


    suspend fun removeCategory(values: List<GroceryListEntity>) {
        withContext(Dispatchers.IO) {
            values.forEach {
                databaseApp.groceryListDao().delete(it)
            }
        }
    }

    suspend fun updateCategory(value: GroceryListEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.groceryListDao().update(value)
        }
    }
}