package info.imdang.imdang.core.data.datasource.model

import info.imdang.imdang.core.domain.model.JoinRequestData

data class JoinRequestEntity(
    val nickname: String,
    val birthDate: String,
    val gender: String,
    val deviceToken: String,
)

fun JoinRequestData.toData() = JoinRequestEntity(
    nickname = nickname,
    birthDate = birthDate,
    gender = gender,
    deviceToken = deviceToken
)