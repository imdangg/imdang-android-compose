package info.imdang.imdang.core.component.textinput

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import info.imdang.imdang.core.component.theme.Error

@Composable
internal fun TextInputFooter(
    errorMessage: String,
) {
    Text(
        text = errorMessage,
        style = descriptionTextInputStyle,
        color = Error
    )
}