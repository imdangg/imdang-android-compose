package info.imdang.imdang.core.component.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.Gray25
import info.imdang.imdang.core.component.theme.Gray400
import info.imdang.imdang.core.component.theme.Gray500
import info.imdang.imdang.core.component.theme.Gray700
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.imdang.core.component.theme.Orange200
import info.imdang.imdang.core.component.theme.Orange300
import info.imdang.imdang.core.component.theme.Orange400
import info.imdang.imdang.core.component.theme.Orange50
import info.imdang.imdang.core.component.theme.Orange500
import info.imdang.imdang.core.component.theme.White

@Composable
fun MainButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize,
    text: String? = null,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val buttonColors = determineButtonColors(
        isPressed = isPressed,
        defaultColors = ButtonColors(
            containerColor = Orange500,
            contentColor = White,
            disabledContainerColor = Gray100,
            disabledContentColor = Gray500
        ),
        pressedColors = ButtonDefaults.buttonColors(
            containerColor = Orange400,
            contentColor = White
        )
    )

    val content: @Composable RowScope.() -> Unit = {
        text?.let {
            val textStyle = buttonTextStyle(buttonSize = buttonSize)
            Text(text = it, style = textStyle)
        }
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        shape = buttonShape(buttonSize),
        enabled = enabled,
        colors = buttonColors,
        contentPadding = buttonContentPadding(buttonSize),
        content = content
    )
}

@Composable
fun SubButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize,
    text: String? = null,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val buttonColors = determineButtonColors(
        isPressed = isPressed,
        defaultColors = ButtonColors(
            containerColor = White,
            contentColor = Orange500,
            disabledContainerColor = White,
            disabledContentColor = Orange300
        ),
        pressedColors = ButtonDefaults.buttonColors(
            containerColor = Orange50,
            contentColor = Orange500
        )
    )

    val borderColor = when {
        !enabled -> Orange200
        isPressed -> Orange300
        else -> Orange500
    }

    val content: @Composable RowScope.() -> Unit = {
        text?.let {
            val textStyle = buttonTextStyle(buttonSize = buttonSize)
            Text(text = it, style = textStyle)
        }
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        shape = buttonShape(buttonSize),
        enabled = enabled,
        colors = buttonColors,
        border = BorderStroke(1.dp, borderColor),
        contentPadding = buttonContentPadding(buttonSize),
        content = content
    )
}

@Composable
fun GhostButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize,
    text: String? = null,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val buttonColors = determineButtonColors(
        isPressed = isPressed,
        defaultColors = ButtonColors(
            containerColor = White,
            contentColor = Gray700,
            disabledContainerColor = White,
            disabledContentColor = Gray400
        ),
        pressedColors = ButtonDefaults.buttonColors(
            containerColor = Gray25,
            contentColor = Gray700
        )
    )

    val borderColor = when {
        !enabled -> Gray100
        isPressed -> Gray100
        else -> Gray100
    }

    val content: @Composable RowScope.() -> Unit = {
        text?.let {
            val textStyle = buttonTextStyle(buttonSize = buttonSize)
            Text(text = it, style = textStyle)
        }
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource,
        shape = buttonShape(buttonSize),
        enabled = enabled,
        colors = buttonColors,
        border = BorderStroke(1.dp, borderColor),
        contentPadding = buttonContentPadding(buttonSize),
        content = content
    )
}

@ImdangPreview
@Composable
private fun MainButtonsPreview() {
    ImdangAppNewTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            for (buttonSize in ButtonSize.entries) {
                MainButton(
                    onClick = {},
                    modifier = Modifier,
                    buttonSize = buttonSize,
                    text = "Text",
                    enabled = true
                )
            }
        }
    }
}

@ImdangPreview
@Composable
private fun SubButtonsPreview() {
    ImdangAppNewTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            for (buttonSize in ButtonSize.entries) {
                SubButton(
                    onClick = {},
                    modifier = Modifier,
                    buttonSize = buttonSize,
                    text = "Text",
                    enabled = true
                )
            }
        }
    }
}

@ImdangPreview
@Composable
private fun GhostButtonsPreview() {
    ImdangAppNewTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            for (buttonSize in ButtonSize.entries) {
                GhostButton(
                    onClick = {},
                    modifier = Modifier,
                    buttonSize = buttonSize,
                    text = "Text",
                    enabled = true
                )
            }
        }
    }
}