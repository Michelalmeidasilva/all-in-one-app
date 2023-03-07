package com.michel.galileu.viewmodel.grocery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.entities.GroceryListEntity
import com.michel.galileu.data.repository.GroceryListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class GroceriesListsViewModel(application: Application) : AndroidViewModel(application) {
    private val _groceriesListData = MutableStateFlow<List<GroceryListEntity>>(emptyList())
    val groceriesListData = _groceriesListData.asStateFlow()

    private val groceryListRepository = GroceryListRepository(application);


    init {
        viewModelScope.launch {
            _groceriesListData.update {
                groceryListRepository.getGroceriesList();
            }
        }
    }
}