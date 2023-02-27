package com.michel.galileu.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.michel.galileu.data.converters.ListConverters
import com.michel.galileu.data.daos.RecipeDao
import com.michel.galileu.data.room.entities.RecipeEntity

/**
 * The [RoomDatabase] we use in this app.
 */
@Database(
    entities = [
        RecipeEntity::class,
    ],
    version = 1
)

@TypeConverters(ListConverters::class)
abstract class DatabaseApp : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

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

