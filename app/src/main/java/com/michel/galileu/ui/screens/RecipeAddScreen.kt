package com.michel.galileu.ui.screens

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.TextField
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.ui.viewmodel.RecipeAddViewModel
import com.michel.galileu.utils.IOManager
import kotlinx.coroutines.*
import okhttp3.internal.wait

@Composable
fun RequestContentPermission(application: Application, bitmap: MutableState<Bitmap?>) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Column(modifier = Modifier.padding(all = 8.dp)) {
        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)

            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }



            bitmap.value?.let { btm ->
                if (btm.width > btm.height) {
                    Text(text = "Imagem adicionada.")
                } else {
                    Text(text = "Dimensões não suportadas")
                }
            }
        }

        Button(onClick = {
            launcher.launch("image/*")
        }) {
            Row {
                Text(text = "Selecione a imagem.")
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun RecipeAddScreen(
    application: Application,
    recipeAddViewModel: RecipeAddViewModel = viewModel(),
    onSucessfullyCreateRecipe: () -> Unit
) {
    val listItems = remember { mutableStateListOf<String>() }
    var text by rememberSaveable { mutableStateOf("") }
    var preparoText by rememberSaveable { mutableStateOf("") }
    var preparoItems = remember { mutableStateListOf<String>() }
    val manager: IOManager = IOManager()
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val scrollState = rememberScrollState()
    var title by rememberSaveable { mutableStateOf("") }
    var subtitle by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var loading by rememberSaveable { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Column() {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color.White, RoundedCornerShape(5.dp)),
                    value = title,
                    label = { Text("Título") },
                    onValueChange = { it -> title = it },
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
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

            RequestContentPermission(application, bitmap)

            Text("Ingredientes:", style = MaterialTheme.typography.titleLarge)

            Row() {
                TextField(value = text, onValueChange = { text = it })

                OutlinedButton(modifier = Modifier.padding(horizontal = 10.dp),
                    border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(80),
                    onClick = {
                        if (text.isNotEmpty()) {
                            listItems.add(text)
                        }
                    }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                }
            }


            listItems.map { it ->
                OutlinedCard(
                    modifier = Modifier
                        .padding(2.dp)
                        .height(50.dp)
                        .fillMaxWidth(0.7f)
                ) {
                    Text(modifier = Modifier.padding(10.dp), text = it)
                }
            }

            Text(
                "Preparo:",
                style = MaterialTheme.typography.titleLarge
            )

            Row() {
                TextField(value = preparoText, onValueChange = { preparoText = it })

                OutlinedButton(modifier = Modifier.padding(horizontal = 10.dp),
                    border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(80),
                    onClick = {
                        if (preparoText.isNotEmpty()) {
                            preparoItems.add(preparoText)
                        }
                    }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                }
            }

            preparoItems.map { it ->
                OutlinedCard(
                    modifier = Modifier
                        .padding(2.dp)
                        .height(50.dp)
                        .fillMaxWidth(0.7f)
                ) {
                    Text(text = it)
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    try {
                        loading = true;
                        val fileName = "$title.jpeg";

                        GlobalScope.launch() {
                            recipeAddViewModel.addRecipe(
                                recipeEntity = RecipeEntity(
                                    subtitle = subtitle,
                                    title = title,
                                    instructions = preparoItems,
                                    ingredients = listItems,
                                    id = null,
                                    imagePath = fileName
                                )
                            )

                            manager.uploadImage(
                                application = application, fileName = fileName, bitmap = bitmap
                            )

                        }
                        onSucessfullyCreateRecipe()


                    } catch (_: Exception) {

                    } finally {
                        loading = false
                    }

                },
                enabled = !loading
            ) {
                Text(text = "Cadastrar")
            }
        }
    }

}
