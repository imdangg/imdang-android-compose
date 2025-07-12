package info.imdang.ui.login

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.KakaoYellow
import info.imdang.imdang.core.component.theme.White
import info.imdang.ui.R

sealed class LoginPlatform(
    val backgroundColor: Color,
    val textColor: Color,
    val borderColor: Color? = null,
    @DrawableRes val iconRes: Int,
    @StringRes val labelRes: Int
) {
    data object KAKAO : LoginPlatform(
        backgroundColor = KakaoYellow,
        textColor = Gray900,
        iconRes = R.drawable.ic_kakao,
        labelRes = R.string.btn_kakao_login
    )

    data object GOOGLE : LoginPlatform(
        backgroundColor = White,
        textColor = Gray900,
        borderColor = Gray100,
        iconRes = R.drawable.ic_google,
        labelRes = R.string.btn_google_login
    )

    companion object {
        val all = listOf(KAKAO, GOOGLE)
    }
}