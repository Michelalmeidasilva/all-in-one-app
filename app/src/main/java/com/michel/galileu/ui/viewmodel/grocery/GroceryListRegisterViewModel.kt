package com.michel.galileu.ui.viewmodel.grocery

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.michel.galileu.data.entities.GroceryListCategoryEntity
import com.michel.galileu.data.repository.GroceryListCategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class GroceryListRegisterViewModel(application: Application) : AndroidViewModel(application) {
    // continuar daqui: https://medium.com/@asissuthar/simplify-form-validation-using-kotlin-flow-on-android-16c718e3efaa
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var password by mutableStateOf("")
    var mobileNumber by mutableStateOf("")
    var mobileCountryCode by mutableStateOf("")
    var dateOfBirth by mutableStateOf("")

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