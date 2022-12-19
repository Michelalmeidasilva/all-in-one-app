package com.michel.galileu.data

import com.michel.galileu.models.recipe.RecipeModel
import com.michel.galileu.models.recipe.SettingsModel


object Data {
    val settings: List<SettingsModel> = listOf(SettingsModel(name = "Privacy", "test"), SettingsModel(name = "Security", "test"),SettingsModel(name = "About", "test"),  );

    val recipes: List<RecipeModel> = listOf(
        RecipeModel(
            id = "3",
            instructions = listOf("Pegue a cremeira", "Jogue sal"),
            "alergicos ao leite, cuidado.",
            "Strogonoff ao molho da preguiça",
            ingredients = listOf("leite", "creme de leite", "sal")

        ), RecipeModel(
            id = "1",
            instructions = listOf(
            "Em uma panela, misture o frango, o alho, a maionese, o sal e a pimenta.",
            "Em uma frigideira grande, derreta a manteiga e doure a cebola.",
            "Junte o frango temperado até que esteja dourado.",
            "Adicione os cogumelos, o ketchup e a mostarda.",
            "Incorpore o creme de leite e retire do fogo antes de ferver.",
            "Sirva com arroz branco e batata palha."
            ),
            "alergicos ao leite, cuidado.",
            "Melhor receita do mundo",
            ingredients = listOf(
            "3 peitos de frango cortados em cubos",
            "1 dente de alho picado",
            "sal e pimenta a gosto",
            "1 cebola picada",
            "2 colheres (sopa) de maionese",
            "1 colher de manteiga",
            "1/2 copo de ketchup",
            "1/3 copo de mostarda",
            "1 copo de cogumelos",
            "1 copo de creme de leite",
            "batata palha a gosto"

            )

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
