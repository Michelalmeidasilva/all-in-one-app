package com.michel.galileu.ui.viewmodel

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.Data
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.data.repository.RecipeRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(RecipeEntity())
    val uiState: StateFlow<RecipeEntity> = _uiState.asStateFlow();

    private val mRepository: RecipeRepository =
        RecipeRepository(application)

    private var fetchJob: Job? = null;

    fun fetchRecipe(idRecipe: Int?) {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            if (idRecipe !== null) {
                val item = mRepository.getRecipeById(idRecipe)
                _uiState.update {
                    item.copy(
                        id = item.id,
                        subtitle = item.subtitle,
                        ingredients = item.ingredients,
                        instructions = item.instructions
                    )
                }
                _uiState.onStart { }
            }

        }

        fetchJob?.cancel()
    }

}