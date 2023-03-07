package com.michel.galileu.viewmodel.grocery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.entities.GroceryListCategoryEntity
import com.michel.galileu.data.entities.GroceryListEntity
import com.michel.galileu.data.repository.GroceryListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class GroceryFormDataState(
    var title: String = "",
    var categoryList: GroceryListCategoryEntity? = GroceryListCategoryEntity(
        name = "general",
        icon = "Home",
        listCategoryId = 1
    ),
    var description: String = ""
)


class GroceryListRegisterViewModel(application: Application) : AndroidViewModel(application) {
    val groceryListRepository = GroceryListRepository(application)

    private val _uiState: MutableStateFlow<GroceryFormDataState> = MutableStateFlow(
        GroceryFormDataState()
    )
    val uiState: MutableStateFlow<GroceryFormDataState> = _uiState

    fun onChangeFormFieldValue(field: String, newValue: String) = viewModelScope.launch {
        _uiState.update {
            when (field) {
                "title" -> it.copy(title = newValue)
                "description" -> it.copy(description = newValue)
                else -> it
            }
        }
    }


    fun onSubmit() = viewModelScope.launch {
        groceryListRepository.insert(
            GroceryListEntity(
                name = _uiState.value.title,
                priceAmount = null,
                categoryList = null,
                iconList = null,
                descriptionList = _uiState.value.description
            )

        )
    }
}