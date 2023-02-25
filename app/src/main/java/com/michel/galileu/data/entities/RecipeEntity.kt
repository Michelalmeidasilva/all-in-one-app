package com.michel.galileu.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "instructions") val instructions: List<String?>? = null,
    @ColumnInfo(name = "ingredients") val ingredients: List<String?>? = null,
    @ColumnInfo(name = "imagePath") val imagePath: String = ""
)