package info.imdang.core.presentation.model

import info.imdang.imdang.core.domain.model.LoginRequestData

data class LoginRequestModel(
    val provider: String,
    val token: String
)

fun LoginRequestModel.toDomain(): LoginRequestData = LoginRequestData(
    provider = provider,
    token = token
)