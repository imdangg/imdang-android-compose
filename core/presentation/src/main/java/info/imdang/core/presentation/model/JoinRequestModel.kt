package info.imdang.core.presentation.model

import info.imdang.imdang.core.domain.model.JoinRequestData

data class JoinRequestModel(
    val nickname: String,
    val birthDate: String,
    val gender: String,
    val deviceToken: String,
)

fun JoinRequestModel.toDomain(): JoinRequestData = JoinRequestData(
    nickname = nickname,
    birthDate = birthDate,
    gender = gender,
    deviceToken = deviceToken
)