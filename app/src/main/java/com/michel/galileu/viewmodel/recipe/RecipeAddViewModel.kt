package com.michel.galileu.viewmodel.recipe

import android.app.Application
import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.data.repository.RecipeRepository
import com.michel.galileu.ui.screens.recipe.ItemList
import com.michel.galileu.utils.IOManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeAddViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: RecipeRepository =
        RecipeRepository(application)

    fun addRecipe(recipeEntity: RecipeEntity?, onComplete: () -> Unit) {
        viewModelScope.launch {
            if (recipeEntity !== null) {
                mRepository.insertRecipe(recipeEntity);
                onComplete()
            }
        }
    }

    fun uploadImage(application: Application, fileName: String, bitmapState: MutableState<Bitmap?>){
        viewModelScope.launch {
            val manager = IOManager()

            manager.uploadImage(
                application = application, fileName = fileName, bitmap = bitmapState
            )
        }
    }
}