package com.michel.galileu.models.recipe

data class RecipeModel(val id: String = "",  val instructions: List<String> = listOf(), val subtitle: String = "", val title: String = "", val ingredients: List<String> = listOf(), val imagePath: String? = null, val isFetchingRecipes: Boolean = false)