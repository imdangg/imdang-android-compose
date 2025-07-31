package info.imdang.imdang.core.domain.model

data class ReissueData(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int?,
)
