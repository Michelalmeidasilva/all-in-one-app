package com.michel.galileu.ui.screens.grocery

import CustomOutlinedTextField
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.michel.galileu.viewmodel.grocery.GroceryListRegisterViewModel


@Composable
fun GroceryListForm(viewModel: GroceryListRegisterViewModel) {
    val focusManager = LocalFocusManager.current
    val uiState = viewModel.uiState.collectAsState();

    Column(modifier = Modifier.padding(horizontal = 40.dp)) {
        CustomOutlinedTextField(
            modifier = Modifier.padding(top = 20.dp),
            value = uiState.value.title,
            onValueChange = { it -> viewModel.onChangeFormFieldValue("title", it) }
        )

        CustomOutlinedTextField(
            modifier = Modifier.padding(top = 20.dp),
            value = uiState.value.description,
            onValueChange = { it -> viewModel.onChangeFormFieldValue("description", it) }
        )
    }

}


@Composable
fun GroceryListRegisterScreen(
    viewModel: GroceryListRegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onSubmitForm: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {


        GroceryListForm(viewModel)

        Button(
            onClick = {
                viewModel.onSubmit();
                onSubmitForm()
            },
            modifier = Modifier
                .padding(all = 8.dp)
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth(0.8f)
        ) {
            Text("Cadastrar")
        }
    }
}