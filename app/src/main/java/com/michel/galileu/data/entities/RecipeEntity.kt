package com.michel.galileu.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "subtitle") val subtitle: String? = null,
    @ColumnInfo(name = "instructions") val instructions: List<String?>? = null,
    @ColumnInfo(name = "ingredients") val ingredients: List<String?>? = null,
    @ColumnInfo(name = "imagePath") val imagePath: String = ""
)