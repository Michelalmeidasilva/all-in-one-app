package com.michel.galileu.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.data.repository.RecipeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(RecipeEntity())
    val uiState: StateFlow<RecipeEntity> = _uiState.asStateFlow();


    private val mRepository: RecipeRepository =
        RecipeRepository(application)
    
    fun fetchRecipe(idRecipe: Int) {
        viewModelScope.launch {
            _uiState.update {
                val value = mRepository.getRecipeById(idRecipe);
                it.copy(
                    id = value.id,
                    subtitle = value.subtitle,
                    ingredients = value.ingredients,
                    instructions = value.instructions,
                    imagePath = value.imagePath
                )
            }
            _uiState.onStart { }

        }
    }
}