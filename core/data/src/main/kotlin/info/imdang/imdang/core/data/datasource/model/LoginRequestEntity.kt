package info.imdang.imdang.core.data.datasource.model

import info.imdang.imdang.core.domain.model.LoginRequestData

data class LoginRequestEntity(
    val provider: String,
    val token: String
)

fun LoginRequestData.toData() = LoginRequestEntity(
    provider = provider,
    token = token
)