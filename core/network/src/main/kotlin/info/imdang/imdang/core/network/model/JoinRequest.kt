package info.imdang.imdang.core.network.model

import info.imdang.imdang.core.data.datasource.model.JoinRequestEntity
import kotlinx.serialization.Serializable

@Serializable
data class JoinRequest(
    val nickname: String,
    val birthDate: String,
    val gender: String,
    val deviceToken: String,
)

fun JoinRequestEntity.toRemote() = JoinRequest(
    nickname = nickname,
    birthDate = birthDate,
    gender = gender,
    deviceToken = deviceToken
)