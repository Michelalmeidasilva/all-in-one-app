package com.michel.galileu.ui.screens.recipe

import CustomOutlinedTextField
import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.galileu.data.entities.RecipeEntity
import com.michel.galileu.models.RecipeModel
import com.michel.galileu.ui.viewmodel.recipe.RecipeAddViewModel
import com.michel.galileu.utils.changeListType
import kotlinx.coroutines.*
import org.burnoutcrew.reorderable.*

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

    Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        Button(modifier = Modifier.align(Alignment.CenterVertically), onClick = {
            launcher.launch("image/*")
        }) {
            Text(text = "Selecione a imagem")

        }

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)

            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Row(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "check circle"
                    )

                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Imagem adicionada."
                    )
                }
            }
        }
    }
}

enum class ListType {
    BULLET_LIST, ORDERED_LIST, NUMBERED_LIST
}

@Composable
fun RegisterItems(itemsList: MutableList<RecipeModel>, typeList: ListType = ListType.ORDERED_LIST) {
    var text by rememberSaveable { mutableStateOf("") }

    fun addItemToList() {
        if (!text.isBlank()) {
            itemsList.add(RecipeModel(text, false))
            text = "";
        }
    }

    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        CustomOutlinedTextField(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth(0.75f)
                .height(45.dp)
                .background(Color.White, RoundedCornerShape(5.dp))
                .onKeyEvent(
                ) {
                    if (
                        it.nativeKeyEvent.keyCode == KEYCODE_ENTER
                    ) {
                        addItemToList();
                    }
                    true
                },
            value = text,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardAction = KeyboardActions(onDone = { addItemToList() }),
            onValueChange = { text = it }
        )

        IconButton(modifier = Modifier
            .padding(horizontal = 10.dp)
            .background(MaterialTheme.colorScheme.background),
            onClick = { addItemToList() }) {
            Icon(
                modifier = Modifier.size(22.dp),
                imageVector = Icons.Filled.Add,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Add"
            )
        }
    }

    itemsList.mapIndexed { index, it ->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .height(32.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "${changeListType(typeList, index + 1)} ${it.value}",
                modifier = Modifier.clickable(onClick = {
                    it.editable = !it.editable
                }
                )
            )
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun RecipeAddScreen(
    application: Application,
    recipeAddViewModel: RecipeAddViewModel = viewModel(),
    onSuccessfullyCreateRecipe: () -> Unit
) {
    val ingredients = remember { mutableStateListOf<RecipeModel>() }
    val instructionsItems = remember { mutableStateListOf<RecipeModel>() }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val scrollState = rememberScrollState()
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var loading by rememberSaveable { mutableStateOf(false) }

    fun onRegisterRecipe() {
        try {
            loading = true;
            val fileName = if (bitmap.value !== null) "$title.jpeg"; else ""

            recipeAddViewModel.addRecipe(
                recipeEntity = RecipeEntity(
                    description = description,
                    title = title,
                    instructions = instructionsItems.map { it -> it.value },
                    ingredients = ingredients.map { it -> it.value },
                    imagePath = fileName,
                ), onComplete = { onSuccessfullyCreateRecipe() }
            )

            recipeAddViewModel.uploadImage(application, fileName, bitmap)
        } catch (_: Exception) {
        } finally {
            loading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(start = 30.dp, top = 10.dp, end = 30.dp)
        ) {
            Text(
                text = "Cadastro de Receita",
                modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.titleLarge
            )

            Column(modifier = Modifier.padding(top = 12.dp)) {
                CustomOutlinedTextField(
                    value = title,
                    label = { Text(text = "Titulo") },
                    onValueChange = { title = it },
                )

                CustomOutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    value = description,
                    label = { Text(text = "Descrição") },
                    onValueChange = { description = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    maxLines = 3
                )
            }

            RequestContentPermission(application, bitmap)

            Text(
                "Ingredientes:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 10.dp, bottom = 4.dp)
            )

            RegisterItems(itemsList = ingredients, ListType.ORDERED_LIST)

            Text(
                "Preparo:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 10.dp, bottom = 4.dp)
            )

            RegisterItems(itemsList = instructionsItems, ListType.NUMBERED_LIST)

            Button(
                onClick = { onRegisterRecipe() },
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .fillMaxWidth(1f)
            ) {
                Text("Cadastrar")
            }
        }
    }
}
