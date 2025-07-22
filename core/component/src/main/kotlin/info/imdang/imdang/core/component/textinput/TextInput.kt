package info.imdang.imdang.core.component.textinput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import info.imdang.imdang.core.component.theme.ImdangPreview

/**
 * Composable function for TextInput that accepts String value.
 * This is useful for cases where you don't need to handle cursor position and selection.
 */
@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    inputType: TextInputType,
    value: String,
    onValueChanged: (String) -> Unit,
    label: String,
    labelDescription: String,
    placeHolder: String,
    maxLength: Int,
    enabled: Boolean = true,
    isError: Boolean,
    errorMessage: String? = null,
    isSuccess: Boolean,
) {
    Column(
        modifier = modifier
    ) {
        TextInputHeader(
            inputType = inputType,
            value = value,
            label = label,
            labelDescription = labelDescription,
            maxLength = maxLength,
            enabled = enabled,
            isError = isError,
            isSuccess = isSuccess,
        )

        Spacer(Modifier.height(8.dp))

        TextInputBody(
            inputType = inputType,
            value = value,
            onValueChanged = onValueChanged,
            placeHolderText = placeHolder,
            enabled = enabled,
            isError = isError,
        )

        Spacer(Modifier.height(8.dp))

        if (enabled && isError) {
            errorMessage?.let {
                TextInputFooter(errorMessage = it)
            }
        }
    }
}

/**
 * Composable function for TextInput that accepts TextFieldValue.
 * This is useful for cases where you need to handle cursor position and selection.
 */
@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    inputType: TextInputType,
    value: TextFieldValue,
    onValueChanged: (TextFieldValue) -> Unit,
    label: String,
    labelDescription: String,
    placeHolder: String,
    maxLength: Int,
    enabled: Boolean = true,
    isError: Boolean,
    errorMessage: String? = null,
    isSuccess: Boolean,
) {
    Column(modifier = modifier) {
        TextInputHeader(
            inputType = inputType,
            value = value.text,
            label = label,
            labelDescription = labelDescription,
            maxLength = maxLength,
            enabled = enabled,
            isError = isError,
            isSuccess = isSuccess,
        )

        Spacer(Modifier.height(8.dp))

        TextInputBody(
            inputType = inputType,
            value = value,
            onValueChanged = onValueChanged,
            placeHolderText = placeHolder,
            enabled = enabled,
            isError = isError,
        )

        Spacer(Modifier.height(8.dp))

        if (enabled && isError) {
            errorMessage?.let {
                TextInputFooter(errorMessage = it)
            }
        }
    }
}

@ImdangPreview
@Composable
private fun InputPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextInput(
            modifier = Modifier.fillMaxWidth(),
            inputType = TextInputType.INPUT,
            value = "",
            onValueChanged = {},
            label = "InputText - Default",
            labelDescription = "오류 메세지 텍스트",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            isError = false,
            isSuccess = false
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            inputType = TextInputType.INPUT,
            value = "",
            onValueChanged = {},
            label = "InputText - Success",
            labelDescription = "오류 메세지 텍스트",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            isError = false,
            isSuccess = true
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            inputType = TextInputType.INPUT,
            value = "",
            onValueChanged = {},
            label = "InputText - Error",
            labelDescription = "오류 메세지 텍스트",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            isError = true,
            isSuccess = false
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            inputType = TextInputType.INPUT,
            value = "",
            onValueChanged = {},
            label = "InputText - Disabled",
            labelDescription = "오류 메세지 텍스트",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            enabled = false,
            isError = false,
            isSuccess = false
        )
    }
}

@ImdangPreview
@Composable
private fun TextInputSingleLinePreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextInput(
            modifier = Modifier
                .fillMaxWidth()
                .height(105.dp),
            inputType = TextInputType.SINGLE_LINE,
            value = "",
            onValueChanged = {},
            label = "SingleLine - Default",
            labelDescription = "최소 2자~최대10자",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            isError = false,
            errorMessage = "오류 메세지 텍스트",
            isSuccess = false
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            inputType = TextInputType.SINGLE_LINE,
            value = "",
            onValueChanged = {},
            label = "SingleLine - Success",
            labelDescription = "최소 2자~최대10자",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            isError = false,
            errorMessage = "오류 메세지 텍스트",
            isSuccess = true
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            inputType = TextInputType.SINGLE_LINE,
            value = "",
            onValueChanged = {},
            label = "SingleLine - Error",
            labelDescription = "최소 2자~최대10자",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            isError = true,
            errorMessage = "오류 메세지 텍스트",
            isSuccess = false
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            inputType = TextInputType.SINGLE_LINE,
            value = "",
            onValueChanged = {},
            label = "SingleLine - Disabled",
            labelDescription = "최소 2자~최대10자",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            enabled = false,
            isError = false,
            errorMessage = "오류 메세지 텍스트",
            isSuccess = false
        )
    }
}

@ImdangPreview
@Composable
private fun TextInputMultiLinePreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextInput(
            modifier = Modifier.fillMaxWidth(),
            inputType = TextInputType.MULTI_LINE,
            value = "",
            onValueChanged = {},
            label = "MultiLine - Default",
            labelDescription = "최소 2자~최대10자",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            isError = false,
            errorMessage = "오류 메세지 텍스트",
            isSuccess = false
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            inputType = TextInputType.MULTI_LINE,
            value = "",
            onValueChanged = {},
            label = "MultiLine - Success",
            labelDescription = "최소 2자~최대10자",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            isError = false,
            errorMessage = "오류 메세지 텍스트",
            isSuccess = true
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            inputType = TextInputType.MULTI_LINE,
            value = "",
            onValueChanged = {},
            label = "MultiLine - Error",
            labelDescription = "최소 2자~최대10자",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            isError = true,
            errorMessage = "오류 메세지 텍스트",
            isSuccess = false
        )

        TextInput(
            modifier = Modifier.fillMaxWidth(),
            inputType = TextInputType.MULTI_LINE,
            value = "",
            onValueChanged = {},
            label = "MultiLine - Disabled",
            labelDescription = "최소 2자~최대10자",
            placeHolder = "플레이스 홀더",
            maxLength = 10,
            enabled = false,
            isError = false,
            errorMessage = "오류 메세지 텍스트",
            isSuccess = false
        )
    }
}