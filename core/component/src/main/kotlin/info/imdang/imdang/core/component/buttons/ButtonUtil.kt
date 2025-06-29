package info.imdang.imdang.core.component.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.imdang.imdang.core.component.theme.pretendardFont


internal fun buttonShape(buttonSize: ButtonSize): RoundedCornerShape {
    return when (buttonSize) {
        ButtonSize.W -> RoundedCornerShape(0.dp)
        ButtonSize.L -> RoundedCornerShape(8.dp)
        ButtonSize.M -> RoundedCornerShape(8.dp)
        ButtonSize.S -> RoundedCornerShape(6.dp)
    }
}

internal fun buttonContentPadding(buttonSize: ButtonSize): PaddingValues {
    return when (buttonSize) {
        ButtonSize.W -> {
            PaddingValues(horizontal = 16.dp, vertical = 17.dp)
        }

        ButtonSize.L -> {
            PaddingValues(horizontal = 16.dp, vertical = 17.dp)
        }

        ButtonSize.M -> {
            PaddingValues(horizontal = 12.dp, vertical = 11.dp)
        }

        ButtonSize.S -> {
            PaddingValues(horizontal = 12.dp, vertical = 7.5.dp)
        }
    }
}

@Composable
internal fun buttonTextStyle(buttonSize: ButtonSize): TextStyle {
    return when (buttonSize) {
        ButtonSize.W -> {
            TextStyle(
                fontFamily = pretendardFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = (16 * 1.4).sp,
            )
        }

        ButtonSize.L -> {
            TextStyle(
                fontFamily = pretendardFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = (16 * 1.4).sp,
            )
        }

        ButtonSize.M -> {
            TextStyle(
                fontFamily = pretendardFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = (14 * 1.4).sp,
            )
        }

        ButtonSize.S -> {
            TextStyle(
                fontFamily = pretendardFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = (12 * 1.4).sp,
            )
        }
    }
}

@Composable
internal fun determineButtonColors(
    isPressed: Boolean,
    defaultColors: ButtonColors,
    pressedColors: ButtonColors,
): ButtonColors {
    return if (isPressed) {
        pressedColors
    } else {
        defaultColors
    }
}

enum class ButtonSize {
    W,
    L,
    M,
    S,
}