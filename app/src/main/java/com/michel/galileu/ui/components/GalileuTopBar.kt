
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.michel.galileu.navigation.RecipesNavigation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalileuTopBar(currentScreen: String = "", onSearchValue: () -> Unit){
    var valueSearch by rememberSaveable { mutableStateOf("Text") }
    var searchIconPressed by rememberSaveable { mutableStateOf(false) }
    var isFocused  by rememberSaveable { mutableStateOf(false) }

    TopAppBar(modifier = Modifier.padding(all = 8.dp),
        title = {
            Row( verticalAlignment = Alignment.CenterVertically) {
//                Text(text = currentScreen)
                TextField(
                    modifier = Modifier.width(300.dp).height(60.dp),
                    value = valueSearch , label= {Text(" ")},
                    shape = RoundedCornerShape(percent = 100),
                    onValueChange = { it -> valueSearch = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Gray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }

        },
        actions = {
            if(currentScreen == RecipesNavigation.name) {
                IconButton(onClick = { onSearchValue()}) {
                    Icon(Icons.Filled.Search, contentDescription = "Search description")
                }
            }

        }
    )
}