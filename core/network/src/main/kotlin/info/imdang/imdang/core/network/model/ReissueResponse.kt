package info.imdang.imdang.core.network.model

import info.imdang.imdang.core.data.datasource.model.ReissueEntity
import info.imdang.imdang.core.network.RemoteMapper
import kotlinx.serialization.Serializable

@Serializable
data class ReissueResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int,
) : RemoteMapper<ReissueEntity> {
    override fun toData(): ReissueEntity =
        ReissueEntity(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiresIn = expiresIn
        )
}