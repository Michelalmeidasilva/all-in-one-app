package com.michel.galileu.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.michel.galileu.data.converters.ListConverters
import com.michel.galileu.data.daos.GroceryListCategoryDao
import com.michel.galileu.data.daos.GroceryListDao
import com.michel.galileu.data.daos.ProductCategoryDao
import com.michel.galileu.data.daos.ProductDao
import com.michel.galileu.data.daos.RecipeDao
import com.michel.galileu.data.room.entities.GroceryListCategoryEntity
import com.michel.galileu.data.room.entities.GroceryListEntity
import com.michel.galileu.data.room.entities.ProductCategoryEntity
import com.michel.galileu.data.room.entities.ProductEntity
import com.michel.galileu.data.room.entities.RecipeEntity

/**
 * The [RoomDatabase] we use in this app.
 */
@Database(
    entities = [
        RecipeEntity::class,
        GroceryListEntity::class,
        GroceryListCategoryEntity::class,
        ProductEntity::class,
        ProductCategoryEntity::class
    ],
    version = 2
)

@TypeConverters(ListConverters::class)
abstract class DatabaseApp : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun groceryListDao(): GroceryListDao
    abstract fun groceryListCategoryDao(): GroceryListCategoryDao
    abstract fun productDao(): ProductDao
    abstract fun productCategoryDao(): ProductCategoryDao
}

private lateinit var INSTANCE: DatabaseApp


fun getDatabase(context: Context): DatabaseApp {
    synchronized(DatabaseApp::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                DatabaseApp::class.java,
                "galileu-app"
            ).build()
        }
    }
    return INSTANCE
}

