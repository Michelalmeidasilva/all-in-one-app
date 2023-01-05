package com.michel.galileu.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.data.repository.RecipeRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecipeAddViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: RecipeRepository =
        RecipeRepository(application)

    @OptIn(DelicateCoroutinesApi::class)
    fun addRecipe(recipeEntity: RecipeEntity?, onComplete: () -> Unit) {
        println(recipeEntity.toString())
        GlobalScope.launch {
            viewModelScope.launch {
                if (recipeEntity !== null) {
                    mRepository.insertRecipe(recipeEntity);
                    onComplete()
                }
            }
        }

    }
}