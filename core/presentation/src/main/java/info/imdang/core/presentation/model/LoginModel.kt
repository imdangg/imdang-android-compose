package info.imdang.core.presentation.model

import info.imdang.imdang.core.domain.model.LoginData

data class LoginModel(
    val memberId: String,
    val isJoined: Boolean,
    val accessToken: String,
    val refreshToken: String,
)

fun LoginData.toPresentation(): LoginModel = LoginModel(
    memberId = memberId,
    isJoined = isJoined,
    accessToken = accessToken,
    refreshToken = refreshToken
)