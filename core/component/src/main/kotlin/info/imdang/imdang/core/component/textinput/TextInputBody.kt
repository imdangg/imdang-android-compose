package info.imdang.imdang.core.component.textinput

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import info.imdang.core.component.R
import info.imdang.imdang.core.component.theme.Gray400

/**
 * Composable function for TextInputBody that accepts String value.
 */
@Composable
internal fun TextInputBody(
    inputType: TextInputType,
    value: String,
    onValueChanged: (String) -> Unit,
    placeHolderText: String,
    enabled: Boolean,
    isError: Boolean,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val inputHeight = if (inputType == TextInputType.MULTI_LINE) {
        180.dp
    } else {
        52.dp
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(inputHeight),
        value = value,
        onValueChange = onValueChanged,
        textStyle = inputTextBodyTextStyle,
        placeholder = {
            Text(
                text = placeHolderText,
                style = inputTextBodyTextStyle,
                color = Gray400
            )
        },
        trailingIcon = {
            if (inputType != TextInputType.MULTI_LINE) {
                if (value.isNotEmpty() && isFocused) {
                    Icon(
                        painter = painterResource(R.drawable.circle_cancle),
                        contentDescription = "Text Clear Icon",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onValueChanged("") }
                    )
                }
            }
        },
        interactionSource = interactionSource,
        singleLine = inputType != TextInputType.MULTI_LINE,
        enabled = enabled,
        isError = isError,
        shape = RoundedCornerShape(8.dp),
        colors = outlinedTextFieldColor()
    )
}

/**
 * Composable function for TextInputBody that accepts TextFieldValue.
 */
@Composable
internal fun TextInputBody(
    inputType: TextInputType,
    value: TextFieldValue,
    onValueChanged: (TextFieldValue) -> Unit,
    placeHolderText: String,
    enabled: Boolean,
    isError: Boolean,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val inputHeight = if (inputType == TextInputType.MULTI_LINE) 180.dp else 52.dp

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(inputHeight),
        value = value,
        onValueChange = onValueChanged,
        textStyle = inputTextBodyTextStyle,
        placeholder = {
            Text(
                text = placeHolderText,
                style = inputTextBodyTextStyle,
                color = Gray400
            )
        },
        trailingIcon = {
            if (inputType != TextInputType.MULTI_LINE) {
                if (value.text.isNotEmpty() && isFocused) {
                    Icon(
                        painter = painterResource(R.drawable.circle_cancle),
                        contentDescription = "Text Clear Icon",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                onValueChanged(TextFieldValue(""))
                            }
                    )
                }
            }
        },
        interactionSource = interactionSource,
        singleLine = inputType != TextInputType.MULTI_LINE,
        enabled = enabled,
        isError = isError,
        shape = RoundedCornerShape(8.dp),
        colors = outlinedTextFieldColor()
    )
}