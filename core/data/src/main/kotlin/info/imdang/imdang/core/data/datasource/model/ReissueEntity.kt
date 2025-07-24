package info.imdang.imdang.core.data.datasource.model

import info.imdang.imdang.core.data.datasource.DataMapper
import info.imdang.imdang.core.domain.model.ReissueData

data class ReissueEntity(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int,
) : DataMapper<ReissueData> {
    override fun toDomain(): ReissueData =
        ReissueData(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiresIn = expiresIn
        )
}