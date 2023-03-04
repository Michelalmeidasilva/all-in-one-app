package com.michel.galileu.ui.screens.grocery

import CustomOutlinedTextField
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp


@Composable
fun GroceryListForm() {
    val focusManager = LocalFocusManager.current


    
    Box(modifier = Modifier.fillMaxSize()){
        
        CustomOutlinedTextField(value = , onValueChange = )
    }
    
}


@Composable
fun GroceryListRegisterScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Button(
            onClick = {},
            modifier = Modifier
                .padding(all = 8.dp)
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth(0.8f)
        ) {
            Text("Cadastrar")
        }
    }
}