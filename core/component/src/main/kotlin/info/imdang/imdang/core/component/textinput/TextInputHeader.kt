package info.imdang.imdang.core.component.textinput

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.imdang.core.component.R
import info.imdang.imdang.core.component.theme.Error
import info.imdang.imdang.core.component.theme.Gray500
import info.imdang.imdang.core.component.theme.Gray700
import info.imdang.imdang.core.component.theme.Orange500
import info.imdang.imdang.core.component.theme.pretendardFont

@Composable
internal fun TextInputHeader(
    inputType: TextInputType,
    value: String,
    label: String,
    labelDescription: String,
    maxLength: Int,
    enabled: Boolean,
    isError: Boolean,
    isSuccess: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Label 텍스트
        Text(
            text = label,
            style = TextStyle(
                fontFamily = pretendardFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = (14 * 1.4).sp,
            ),
            color = Gray700
        )
        // 상태 아이콘
        if (isError || isSuccess) {
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(if (isError) R.drawable.circle_sign_red else R.drawable.circle_check_orange),
                contentDescription = "State Icon",
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        // 우측 설명 & 글자수 표시
        when (inputType) {
            TextInputType.INPUT -> {
                if (isError) {
                    Text(
                        text = labelDescription,
                        style = descriptionTextInputStyle,
                        color = if (enabled) Error else Gray500
                    )
                }
            }

            TextInputType.SINGLE_LINE -> {
                val countColor = when {
                    isError -> Error
                    isSuccess -> Orange500
                    else -> Gray500
                }

                Text(
                    text = buildAnnotatedString {
                        append(labelDescription)
                        if (value.isNotEmpty() && (isError || isSuccess)) {
                            withStyle(
                                style = SpanStyle(
                                    color = if (enabled) countColor else Gray500
                                )
                            ) {
                                append(" (${value.length}/$maxLength)")
                            }
                        }
                    },
                    style = descriptionTextInputStyle,
                    color = Gray500
                )
            }

            TextInputType.MULTI_LINE -> {
                Text(
                    text = if (value.isNotEmpty() && (isError || isSuccess)) {
                        " (${value.length}/$maxLength)"
                    } else {
                        labelDescription
                    },
                    style = descriptionTextInputStyle,
                    color = Gray500
                )
            }
        }
    }
}