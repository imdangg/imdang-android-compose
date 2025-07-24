package info.imdang.imdang.core.data.datasource.model

import info.imdang.imdang.core.domain.model.ReissueRequestData

data class ReissueRequestEntity(
    val memberId: String,
    val refreshToken: String,
)

fun ReissueRequestData.toData() = ReissueRequestEntity(
    memberId = memberId,
    refreshToken = refreshToken,
)