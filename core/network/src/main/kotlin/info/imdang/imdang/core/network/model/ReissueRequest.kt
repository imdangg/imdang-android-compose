package info.imdang.imdang.core.network.model

import info.imdang.imdang.core.data.datasource.model.ReissueRequestEntity
import kotlinx.serialization.Serializable

@Serializable
data class ReissueRequest(
    val memberId: String,
    val refreshToken: String,
)

fun ReissueRequestEntity.toRemote() = ReissueRequest(
    memberId = memberId,
    refreshToken = refreshToken,
)