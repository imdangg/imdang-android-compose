package info.imdang.imdang.core.network.model

import info.imdang.imdang.core.data.datasource.model.LoginEntity
import info.imdang.imdang.core.network.RemoteMapper

data class LoginResponse(
    val memberId: String,
    val isJoined: Boolean,
    val accessToken: String,
    val refreshToken: String,
) : RemoteMapper<LoginEntity> {
    override fun toData(): LoginEntity =
        LoginEntity(
            memberId = memberId,
            isJoined = isJoined,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
}