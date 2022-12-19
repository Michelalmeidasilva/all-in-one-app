package com.michel.galileu.data

import com.michel.galileu.models.recipe.RecipeModel

object RecipesData {
    val recipes: List<RecipeModel> = listOf(
        RecipeModel(
            id = "3",
            instructions = listOf("Pegue a cremeira", "Jogue sal"),
            "alergicos ao leite, cuidado.",
            "Strogonoff ao molho da preguiça",
            ingredients = listOf("leite", "creme de leite", "sal")

        ), RecipeModel(
            id = "1",
            instructions = listOf("Pegue a cremeira", "Jogue sal"),
            "alergicos ao leite, cuidado.",
            "Melhor receita do mundo",
            ingredients = listOf("leite", "creme de leite", "sal")

        ), RecipeModel(
            id = "2",
            instructions = listOf("Pegue a cremeira", "Jogue sal"),
            "alergicos ao leite, cuidado.",
            "Essa é top",
            ingredients = listOf("leite", "creme de leite", "sal")

        ),

    )

    fun getRecipe(recipeId: String?): RecipeModel {
        return recipes.first { it.id == recipeId }
    }
}
