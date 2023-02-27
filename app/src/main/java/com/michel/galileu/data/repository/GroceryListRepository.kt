package com.michel.galileu.data.repository

import android.app.Application
import com.michel.galileu.data.room.DatabaseApp
import com.michel.galileu.data.room.getDatabase

class GroceryListRepository(
    application: Application,
) {
    private val databaseApp: DatabaseApp = getDatabase(application)

}