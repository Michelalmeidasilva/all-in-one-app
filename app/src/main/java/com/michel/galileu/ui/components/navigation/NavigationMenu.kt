@file:Suppress("PreviewAnnotationInFunctionWithParameters")

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.michel.galileu.navigation.bottomNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun GalileuNavBar(onClickNavBar: (value: String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.Center),
            columns = GridCells.Adaptive(110.dp),
            contentPadding = PaddingValues(
                4.dp
            ),
            content = {
                items(bottomNavItems.size) { index ->
                    val it = bottomNavItems[index]

                    Card(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp)
                            .height(90.dp)
                            .align(Alignment.Center),
                        onClick = { onClickNavBar(it.route) }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.width(110.dp)) {

                                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                    it.icon?.let { it1 ->
                                        Icon(
                                            imageVector = it1,
                                            contentDescription = "${it.name} Icon",
                                            modifier = Modifier.size(26.dp),
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }

                                Text(
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .align(Alignment.CenterHorizontally),
                                    text = it.name,
                                    fontWeight = FontWeight.SemiBold,
                                    style = TextStyle(color = Color.Gray, fontSize = 16.sp)
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}