package info.imdang.imdang.core.network.model.base

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    val code : Int,
    val message : String,
)
