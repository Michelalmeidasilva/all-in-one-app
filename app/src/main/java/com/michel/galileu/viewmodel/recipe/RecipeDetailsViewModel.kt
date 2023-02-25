package com.michel.galileu.viewmodel.recipe


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.data.repository.RecipeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class RecipeDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(RecipeEntity())
    val uiState: StateFlow<RecipeEntity> = _uiState.asStateFlow();
    val editMode = MutableStateFlow(false);


    private val mRepository: RecipeRepository =
        RecipeRepository(application)


    fun updateRecipe(recipe: RecipeEntity){
        viewModelScope.launch {
            _uiState.update{
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