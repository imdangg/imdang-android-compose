package info.imdang.imdang.core.component.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import info.imdang.core.component.R
import androidx.compose.ui.text.font.FontVariation

// Set of Material typography styles to start with
val pretendardFont = FontFamily(
    Font(R.font.pretendard_variable)
)


val Typography = Typography(
    // Regular 폰트 (FontWeight.Normal = 400)
    bodyLarge = TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),

    // Medium 폰트 (FontWeight.Medium = 500)
    labelLarge = TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 18.sp * 1.5,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 16.sp * 1.5,
        letterSpacing = 0.5.sp
    ),

    // SemiBold 폰트 (FontWeight.SemiBold = 600)

    titleMedium = TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 18.sp *1.4,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 16.sp*1.4,
        letterSpacing = 0.15.sp
    ),

    // Bold 폰트 (FontWeight.Bold = 700)
    displayLarge = TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = pretendardFont,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    )
)
