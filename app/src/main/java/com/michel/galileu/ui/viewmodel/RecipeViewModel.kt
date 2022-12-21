package com.michel.galileu.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.Data
import com.michel.galileu.models.recipe.RecipeModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeModel())
    val uiState: StateFlow<RecipeModel> = _uiState.asStateFlow();

    val isFetchingData = MutableLiveData(false)

    private var fetchJob: Job? = null;

    fun fetchRecipe(idRecipe: String?){
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            val item = Data.getRecipe(idRecipe);
            _uiState.update {
                item.copy(id = item.id, subtitle = item.subtitle, ingredients = item.ingredients, instructions = item.instructions)
            }
            _uiState.onStart {  }
        }

        fetchJob?.cancel()
    }

}