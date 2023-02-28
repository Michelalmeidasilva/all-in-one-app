package com.michel.galileu.data.repository

import android.app.Application
import com.michel.galileu.data.entities.ProductEntity
import com.michel.galileu.data.room.DatabaseApp
import com.michel.galileu.data.room.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(
    application: Application,
) {

    private val databaseApp: DatabaseApp = getDatabase(application)


    suspend fun insertProduct(value: ProductEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.productDao().insert(value)
        }
    }

    suspend fun getProductById(id: Int): ProductEntity {
        var data: ProductEntity;
        withContext(Dispatchers.IO) {
            data = databaseApp.productDao().getById(id);
        }
        return data;
    }

    suspend fun getProducts(): List<ProductEntity> {
        var data: List<ProductEntity>;

        withContext(Dispatchers.IO) {
            data = databaseApp.productDao().getAll();
        }

        return data;
    }


    suspend fun removeProduct(values: List<ProductEntity>) {
        withContext(Dispatchers.IO) {
            values.forEach {
                databaseApp.productDao().delete(it)
            }
        }
    }

    suspend fun updateProduct(value: ProductEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.productDao().update(value)
        }
    }


}