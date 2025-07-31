package info.imdang.core.presentation.model

import info.imdang.imdang.core.domain.model.ReissueData

data class ReissueModel(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int?,
)

fun ReissueData.toPresentation(): ReissueModel = ReissueModel(
    accessToken = accessToken,
    refreshToken = refreshToken,
    expiresIn = expiresIn
)
