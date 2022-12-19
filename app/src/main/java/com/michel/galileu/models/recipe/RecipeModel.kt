package com.michel.galileu.models.recipe

data class RecipeModel(val id: String, val instructions: List<String>, val subtitle: String, val title: String, val ingredients: List<String>)