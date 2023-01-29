import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.michel.galileu.navigation.bottomNavItems


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalileuNavBar(onClickNavBar: (value: String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {


        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(bottomNavItems.size) { index ->
                val it = bottomNavItems[index]
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .width(60.dp),

                    onClick = { onClickNavBar(it.route) }
                ) {
                    Column() {
                        Text(
                            text = it.name,
                            fontWeight = FontWeight.SemiBold,
                            style = TextStyle(color = Color.White)
                        )
                        it.icon?.let { it1 ->
                            Icon(
                                imageVector = it1,
                                contentDescription = "${it.name} Icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }

    }
}