package info.imdang.imdang.core.data.datasource.model

import info.imdang.imdang.core.data.datasource.DataMapper
import info.imdang.imdang.core.domain.model.LoginData

data class LoginEntity(
    val memberId: String,
    val isJoined: Boolean,
    val accessToken: String,
    val refreshToken: String,
) : DataMapper<LoginData> {
    override fun toDomain(): LoginData =
        LoginData(
            memberId = memberId,
            isJoined = isJoined,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
}
