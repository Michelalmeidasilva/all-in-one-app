package com.michel.galileu.viewmodel.recipe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.michel.galileu.data.entities.RecipeEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class RecipeMenuViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(RecipeEntity())
    val uiState: StateFlow<RecipeEntity> = _uiState.asStateFlow();


}