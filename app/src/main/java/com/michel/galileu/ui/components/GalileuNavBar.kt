import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.michel.galileu.navigation.bottomNavItems


@Composable
fun GalileuNavBar(currentScreen: String?, onClickNavBar: (value: String) -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
        bottomNavItems.forEach {
            val selected = it.route == currentScreen;
            NavigationBarItem(selected = selected, onClick = { onClickNavBar(it.route) }, label = {
                Text(
                    text = it.name,
                    fontWeight = FontWeight.SemiBold,
                    style = TextStyle(color = Color.White)
                )
            }, icon = {
                it.icon?.let { it1 ->
                    Icon(
                        imageVector = it1,
                        contentDescription = "${it.name} Icon",
                        tint = if (selected == true) MaterialTheme.colorScheme.primary else Color.White
                    )
                }
            })
        }
    }
}