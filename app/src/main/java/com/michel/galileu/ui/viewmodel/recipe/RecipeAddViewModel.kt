package com.michel.galileu.ui.viewmodel.recipe

import android.app.Application
import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.data.repository.RecipeRepository
import com.michel.galileu.utils.IOManager
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

    fun uploadImage(
        application: Application,
        fileName: String,
        bitmapState: MutableState<Bitmap?>
    ) {
        viewModelScope.launch {
            val manager = IOManager()

            manager.uploadImage(
                application = application, fileName = fileName, bitmap = bitmapState
            )
        }
    }
}