package com.michel.galileu.viewmodel.recipe

import android.app.Application
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.repository.RecipeRepository
import com.michel.galileu.ui.screens.recipe.ItemList
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class RecipeScreenUiState(
    val isAnyValueSelected: Boolean = false,
)


class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(RecipeScreenUiState())
    val uiState = _uiState.asStateFlow();

    private val repository: RecipeRepository = RecipeRepository(application)

    private val _recipesData = MutableStateFlow<List<ItemList>>(emptyList())

    val query = MutableStateFlow(TextFieldValue(""))

    val filteredRecipes = query
        .debounce(200) // low debounce because we are just filtering local data
        .distinctUntilChanged()
        .combine(_recipesData) { queryText, list ->
            val criteria = queryText.text.lowercase()
            if (criteria.isEmpty()) {
                return@combine _recipesData.value
            } else {
                list.filter { recipe ->
                    recipe.value.title.lowercase().let {
                        it.contains(criteria)
                    }
                }
            }
        }








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
            val recipesFiltered = _recipesData.value?.filter{ it.isSelected};

            viewModelScope.launch{
                repository.removeRecipe(recipesFiltered.map { item -> item.value })

                _recipesData.update {
                    val recipesUpdated = repository.getRecipes();
                    recipesUpdated.map { updated -> ItemList(value = updated, isSelected = false) }
                }

                _uiState.update {
                    RecipeScreenUiState(isAnyValueSelected = false)
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
            RecipeScreenUiState(isAnyValueSelected = _recipesData.value.any{ it.isSelected})
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
                _uiState.update {
                    RecipeScreenUiState(isAnyValueSelected = false)
                }

            } catch(err: Exception){
                println(err.stackTrace)
            }
        }

    }

    fun onChangeQuery(value: String) {
        query.update {
            TextFieldValue(value)
        }

    }

}