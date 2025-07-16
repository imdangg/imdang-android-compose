package info.imdang.imdang.core.network.model

data class LoginWrapperResponse(
    val data: LoginResponse?,
    val error: ErrorResponse?
)