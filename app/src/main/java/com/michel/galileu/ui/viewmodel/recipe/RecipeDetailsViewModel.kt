package com.michel.galileu.ui.viewmodel.recipe


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class RecipeDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(RecipeEntity())
    val uiState: StateFlow<RecipeEntity> = _uiState.asStateFlow();
    val editMode = MutableStateFlow(false);


    private val mRepository: RecipeRepository =
        RecipeRepository(application)


    fun updateRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            _uiState.update {
                recipe
            }

            mRepository.updateRecipe(recipe)
        }
    }

    fun fetchRecipe(idRecipe: Int) {
        viewModelScope.launch {
            _uiState.update {
                val value = mRepository.getRecipeById(idRecipe);
                value
            }
            _uiState.onStart { }

        }
    }
}