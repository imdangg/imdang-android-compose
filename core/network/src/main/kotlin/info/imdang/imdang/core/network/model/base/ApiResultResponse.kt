package info.imdang.imdang.core.network.model.base

import kotlinx.serialization.Serializable

@Serializable
data class ApiResultResponse<T>(
    val data: T? = null,
    val error: ApiErrorResponse? = null
)