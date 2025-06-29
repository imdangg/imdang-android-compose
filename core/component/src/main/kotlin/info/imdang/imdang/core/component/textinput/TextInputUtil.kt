package info.imdang.imdang.core.component.textinput

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import info.imdang.imdang.core.component.theme.Error
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.Gray400
import info.imdang.imdang.core.component.theme.Gray50
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.Orange500
import info.imdang.imdang.core.component.theme.White
import info.imdang.imdang.core.component.theme.pretendardFont

internal val inputTextBodyTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = (16 * 1.4).sp,
)

internal val descriptionTextInputStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = (12 * 1.4).sp,
)

@Composable
internal fun outlinedTextFieldColor() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = Gray900, // 포커스가 맞춰진 상태에서 입력 텍스트의 색상입니다.
    unfocusedTextColor = Gray900, // 포커스가 맞춰져 있지 않은 상태에서 입력 텍스트의 색상입니다.
    disabledTextColor = Gray400, // 비활성화된 상태에서 입력 텍스트의 색상입니다.
    errorTextColor = Gray900, // 오류 상태에서 입력 텍스트의 색상입니다.
    focusedContainerColor = White, // 포커스가 맞춰진 상태에서 텍스트 필드의 컨테이너 색상입니다.
    unfocusedContainerColor = White, // 포커스가 맞춰져 있지 않은 상태에서 텍스트 필드의 컨테이너 색상입니다.
    disabledContainerColor = Gray50, // 비활성화된 상태에서 텍스트 필드의 컨테이너 색상입니다.
    errorContainerColor = White, // 오류 상태에서 텍스트 필드의 컨테이너 색상입니다.
    cursorColor = Orange500, // 텍스트 필드의 커서 색상입니다.
    errorCursorColor = Error, // 오류 상태에서 텍스트 필드의 커서 색상입니다.
    focusedBorderColor = Orange500, // 포커스가 맞춰진 상태에서 텍스트 필드의 테두리 색상입니다.
    unfocusedBorderColor = Gray100, // 포커스가 맞춰져 있지 않은 상태에서 텍스트 필드의 테두리 색상입니다.
    disabledBorderColor = Gray100, // 비활성화된 상태에서 텍스트 필드의 테두리 색상입니다.
    errorBorderColor = Error, // 오류 상태에서 텍스트 필드의 테두리 색상입니다.
    focusedPlaceholderColor = Gray400, // 포커스가 맞춰진 상태에서 텍스트 필드의 플레이스홀더 색상입니다.
    unfocusedPlaceholderColor = Gray400, // 포커스가 맞춰져 있지 않은 상태에서 텍스트 필드의 플레이스홀더 색상입니다.
    disabledPlaceholderColor = Gray400, // 비활성화된 상태에서 텍스트 필드의 플레이스홀더 색상입니다.
    errorPlaceholderColor = Gray400, // 오류 상태에서 텍스트 필드의 플레이스홀더 색상입니다.
)

enum class TextInputType {
    INPUT,
    SINGLE_LINE,
    MULTI_LINE
}