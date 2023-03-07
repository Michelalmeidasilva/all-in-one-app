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


    suspend fun insert(value: GroceryListEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.groceryListDao().insert(value)
        }
    }

    suspend fun getGroceryListById(id: Int): GroceryListEntity {
        var data: GroceryListEntity;
        withContext(Dispatchers.IO) {
            data = databaseApp.groceryListDao().getById(id);
        }
        return data;
    }

    suspend fun getGroceriesList(): List<GroceryListEntity> {
        var data: List<GroceryListEntity>;

        withContext(Dispatchers.IO) {
            data = databaseApp.groceryListDao().getAll();
        }

        return data;
    }


    suspend fun removeGroceryList(values: List<GroceryListEntity>) {
        withContext(Dispatchers.IO) {
            values.forEach {
                databaseApp.groceryListDao().delete(it)
            }
        }
    }

    suspend fun updateGroceryList(value: GroceryListEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.groceryListDao().update(value)
        }
    }
}