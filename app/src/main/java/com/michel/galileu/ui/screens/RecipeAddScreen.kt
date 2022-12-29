package com.michel.galileu.ui.screens

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.TextField
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.data.repository.RecipeRepository
import com.michel.galileu.data.room.getDatabase
import com.michel.galileu.ui.viewmodel.RecipeAddViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestContentPermission() {

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Column(modifier = Modifier.padding(all = 14.dp)) {
        Button(modifier = Modifier.padding(top = 30.dp), onClick = {
            launcher.launch("image/*")
        }) {
            Row {
                Text(text = "Selecione a imagem.")
            }

        }

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)

            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                if (btm.width > btm.height) {
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )

                } else {
                    Text(text = "Dimensões não suportadas")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeAddScreen(
    application: Application,
    recipeAddViewModel: RecipeAddViewModel = viewModel()
) {
    val listItems = remember { mutableStateListOf<String>() }
    var text by rememberSaveable { mutableStateOf("") }
    var preparoText by rememberSaveable { mutableStateOf("") }
    var preparoItems = remember { mutableStateListOf<String>() }

    val scrollState = rememberScrollState()
    var title by rememberSaveable { mutableStateOf("") }
    var subtitle by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Column() {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(top = 10.dp)
                        .background(Color.White, RoundedCornerShape(5.dp)),
                    value = title,
                    label = { Text("Título") },
                    onValueChange = { it -> title = it },
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(top = 10.dp)
                        .background(Color.White, RoundedCornerShape(5.dp)),
                    value = subtitle,
                    label = { Text("Sub-título") },
                    onValueChange = { it -> subtitle = it },
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(top = 10.dp)
                        .background(Color.White, RoundedCornerShape(5.dp)),
                    shape = RoundedCornerShape(5.dp),
                    value = description,
                    label = { Text("Descrição") },
                    onValueChange = { description = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    maxLines = 3
                )

            }




            RequestContentPermission()

            Text("Ingredientes:", style = MaterialTheme.typography.titleLarge)

            TextField(value = text, onValueChange = { text = it })

            Button(onClick = { listItems.add(text) }) {

                Text("Adicionar")
            }

            LazyColumn(
                modifier = Modifier
                    .padding(all = 4.dp)
                    .height(250.dp)
            ) {

                listItems.map { it ->
                    item {
                        OutlinedCard() {
                            Text(text = it)
                        }
                    }
                }
            }


            Text(
                "Preparo:",
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.titleLarge
            )

            TextField(value = preparoText, onValueChange = { preparoText = it })

            Button(onClick = { preparoItems.add(preparoText) }) {
                Text("Adicionar")
            }

//            LazyColumn(
//                modifier = Modifier
//                    .padding(all = 4.dp)
//                    .height(250.dp)
//            ) {

            preparoItems.map { it ->
                OutlinedCard() {
                    Text(text = it)
                }
            }
//            }

            Button(onClick = {

                recipeAddViewModel.addRecipe(
                    recipeEntity = RecipeEntity(
                        subtitle = subtitle,
                        title = title,
                        instructions = preparoItems,
                        ingredients = listItems,
                        id = null,
                        imagePath = null
                    )

                )

            }) {

            }
        }
    }

}
