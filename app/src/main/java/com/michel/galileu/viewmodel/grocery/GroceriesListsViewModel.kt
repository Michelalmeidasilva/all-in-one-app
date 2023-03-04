package com.michel.galileu.viewmodel.grocery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.entities.GroceryListCategoryEntity
import com.michel.galileu.data.repository.GroceryListCategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class GroceriesListsViewModel(application: Application) : AndroidViewModel(application) {
    private val _groceriesListData = MutableStateFlow<List<GroceryListCategoryEntity>>(emptyList())
    val groceriesListData = _groceriesListData.asStateFlow()

    private val groceryListCategoryRepository = GroceryListCategoryRepository(application);


    init {
        fetchRecipe();


        println("Values:")
        println(groceriesListData.value.toString())
    }

    fun fetchRecipe() {
        viewModelScope.launch {
            _groceriesListData.update {
                val value = groceryListCategoryRepository.getCategories();
                value
            }
        }
    }
}