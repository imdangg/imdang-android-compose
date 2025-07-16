package info.imdang.imdang.core.domain.model

data class LoginData(
    val memberId: String,
    val isJoined: Boolean,
    val accessToken: String,
    val refreshToken: String,
)
