package info.imdang.imdang.core.network.model

import info.imdang.imdang.core.data.datasource.model.LoginRequestEntity

data class LoginRequest(
    val provider: String,
    val token: String
)

fun LoginRequestEntity.toRemote() = LoginRequest(
    provider = provider,
    token = token
)