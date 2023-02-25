import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    label: @Composable() (() -> Unit)? = null,
    onValueChange: (value: String) -> Unit,
    modifier: Modifier? = null,
    keyboardOptions: KeyboardOptions? = null,
    keyboardAction: KeyboardActions? = null,
    maxLines: Int? = null
) {
    val INPUT_HEIGHT = 60.dp;

    OutlinedTextField(
        modifier = modifier ?: Modifier
            .fillMaxWidth()
            .height(INPUT_HEIGHT)
            .background(Color.White, RoundedCornerShape(5.dp)),
        value = value,
        label = label,
        onValueChange = { onValueChange(it) },
        keyboardOptions = keyboardOptions ?: KeyboardOptions.Default,
        keyboardActions = keyboardAction ?: KeyboardActions.Default,
        maxLines = maxLines ?: 1

    )
}