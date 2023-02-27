package com.michel.galileu.data.repository

import android.app.Application
import com.michel.galileu.data.room.DatabaseApp
import com.michel.galileu.data.room.entities.ProductCategoryEntity
import com.michel.galileu.data.room.entities.ProductEntity
import com.michel.galileu.data.room.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(
    application: Application,
) {

    private val databaseApp: DatabaseApp = getDatabase(application)


    suspend fun insertCategory(value: ProductCategoryEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.productCategoryDao().insert(value)
        }
    }

    suspend fun getCategoryById(id: Int): ProductCategoryEntity {
        var data: ProductCategoryEntity;
        withContext(Dispatchers.IO) {
            data = databaseApp.productCategoryDao().getById(id);
        }
        return data;
    }

    suspend fun getCategories(): List<ProductCategoryEntity> {
        var data: List<ProductCategoryEntity>;

        withContext(Dispatchers.IO) {
            data = databaseApp.productCategoryDao().getAll();
        }

        return data;
    }


    suspend fun removeCategory(values: List<ProductCategoryEntity>) {
        withContext(Dispatchers.IO) {
            values.forEach {
                databaseApp.productCategoryDao().delete(it)
            }
        }
    }

    suspend fun updateCategory(value: ProductCategoryEntity) {
        withContext(Dispatchers.IO) {
            databaseApp.productCategoryDao().update(value)
        }
    }


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