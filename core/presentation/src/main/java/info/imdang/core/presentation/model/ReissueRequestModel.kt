package info.imdang.core.presentation.model

import info.imdang.imdang.core.domain.model.ReissueRequestData

data class ReissueRequestModel(
    val memberId: String,
    val refreshToken: String,
)

fun ReissueRequestModel.toDomain(): ReissueRequestData = ReissueRequestData(
    memberId = memberId,
    refreshToken = refreshToken,
)
