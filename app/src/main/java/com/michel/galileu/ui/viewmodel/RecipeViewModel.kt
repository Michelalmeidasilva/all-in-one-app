package com.michel.galileu.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.repository.RecipeRepository
import com.michel.galileu.ui.recipe.ItemList
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class RecipeScreenUiState(
    val anyValueSelected: Boolean = false,
)


class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableLiveData(RecipeScreenUiState())
    val uiState: LiveData<RecipeScreenUiState> = _uiState;

    private val repository: RecipeRepository = RecipeRepository(application)

    private val _recipesData = MutableLiveData<MutableList<ItemList>>()
    val recipesData: MutableLiveData<MutableList<ItemList>> = _recipesData;

    init {
        try {
            viewModelScope.launch{
                _recipesData.value = repository.getRecipes().map {
                    ItemList(it, isSelected = false);
                }.toMutableList();
            }
        }catch(err: Exception){
            println(err.stackTrace)
        }
    }

    fun removeAllSelectedRecipes(){
        try {
            val recipesFiltered = recipesData.value?.filter{ it.isSelected};
            println(recipesFiltered);

            viewModelScope.launch{
                if (recipesFiltered != null) {
                    repository.removeRecipe(recipesFiltered.map { item -> item.value })
                    _recipesData.value = repository.getRecipes().map {
                        ItemList(it, isSelected = false);
                    }.toMutableList();
                    _uiState.postValue(RecipeScreenUiState(anyValueSelected = false))
                }
            }
        }catch(err: Exception){
            print(err.stackTrace);
        } finally {
            println(_recipesData);
        }
    }

    private fun updateRecipesData(index: Int, recipe: ItemList){
        _recipesData.value?.set(index, recipe)
    }


    private fun hasAnyValueSelected() {
        _uiState.setValue(recipesData.value?.let { RecipeScreenUiState(anyValueSelected = it.any{ it.isSelected}) })
    }

    fun updateSelectedRecipe(recipeData: ItemList, index: Int) {
        if (_recipesData.value?.get(index)?.isSelected == true) {
            updateRecipesData(index, recipeData.copy(isSelected = false));
        } else {
            updateRecipesData(index, recipeData.copy(isSelected = true));
        }

        hasAnyValueSelected()
    }

    fun clearSelectedRecipes() {
        viewModelScope.launch {
            try {
                println(_recipesData.value);

                _recipesData.value?.replaceAll {
                    it.isSelected = false
                    it
                }

                println(_recipesData.value);

            }catch(err: Exception){
                println(err.stackTrace)
            }
        }

    }

    fun isRecipeSelected(index: Int): Boolean {
        println(_recipesData.value!![index])
        return _recipesData.value!![index].isSelected
    }
}