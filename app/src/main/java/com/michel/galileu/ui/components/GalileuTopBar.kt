import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.michel.galileu.navigation.RecipesNavigation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalileuTopBar(currentScreen: String = ""){
    var searchIconPressed = false;
    var valueSearch by rememberSaveable { mutableStateOf("Text") }

    TopAppBar(
        title = {
            if(searchIconPressed){
                TextField(value = valueSearch , label = {
                                                        Text("Testing")
                },  onValueChange = { it -> valueSearch = it } )
            }
            Text(text = currentScreen)
        },
        actions = {
            if(currentScreen == RecipesNavigation.name) {
                IconButton(onClick = { searchIconPressed = true }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search description")
                }
            }

        }
    )
}