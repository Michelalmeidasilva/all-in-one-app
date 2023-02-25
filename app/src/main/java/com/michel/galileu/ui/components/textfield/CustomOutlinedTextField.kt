import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    val inputHeight = 55.dp;

    OutlinedTextField(
        modifier = modifier ?: Modifier
            .fillMaxWidth()
            .height(inputHeight)
            .background(Color.White),
        value = value,
        textStyle = TextStyle.Default.copy(fontSize = 14.sp),
        label = label,
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background),
        onValueChange = { onValueChange(it) },
        keyboardOptions = keyboardOptions ?: KeyboardOptions.Default,
        keyboardActions = keyboardAction ?: KeyboardActions.Default,
        maxLines = maxLines ?: 1

    )
}