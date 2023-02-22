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
    private val _uiState = MutableStateFlow(RecipeScreenUiState())
    val uiState = _uiState.asStateFlow();

    private val repository: RecipeRepository = RecipeRepository(application)

    private val _recipesData = MutableStateFlow<List<ItemList>>(emptyList())
    val recipesData= _recipesData.asStateFlow();

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

            viewModelScope.launch{
                if (recipesFiltered != null) { //
                    repository.removeRecipe(recipesFiltered.map { item -> item.value })

                    _recipesData.update {
                        val recipesUpdated = repository.getRecipes();
                        recipesUpdated.map { updated -> ItemList(value = updated, isSelected = false) }
                    }

                    _uiState.update {
                        RecipeScreenUiState(anyValueSelected = false)
                    }
                }
            }
        }catch(err: Exception){
            print(err.stackTrace);
        } finally {
            println(_recipesData);
        }
    }

    private fun updateRecipesData(index: Int, recipe: ItemList){
        val valuesUpdated = _recipesData.value.mapIndexed{i, item -> if(index == i ){ recipe} else item  }

        _recipesData.update {
            valuesUpdated
        }
    }


    private fun hasAnyValueSelected() {
        _uiState.update {
            RecipeScreenUiState(anyValueSelected = _recipesData.value.any{ it.isSelected})
        }
    }

    fun updateSelectedRecipe(recipeData: ItemList, index: Int) {
        if (_recipesData.value[index].isSelected) {
            updateRecipesData(index, recipeData.copy(isSelected = false));
        } else {
            updateRecipesData(index, recipeData.copy(isSelected = true));
        }

        hasAnyValueSelected()
    }

    fun clearSelectedRecipes() {
        viewModelScope.launch {
            try {
                val clearRecipesUpdated = _recipesData.value.map {
                    it.isSelected = false;
                    it
                }
                _recipesData.update {
                    clearRecipesUpdated

                }

            } catch(err: Exception){
                println(err.stackTrace)
            }
        }

    }

}