package info.imdang.imdang.core.data.datasource.model.mapper

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mappers.ApiErrorModelMapper
import com.skydoves.sandwich.message
import com.skydoves.sandwich.retrofit.statusCode

object ErrorResponseMapper : ApiErrorModelMapper<Exception> {
    override fun map(apiErrorResponse: ApiResponse.Failure.Error): Exception {
        return Exception("API Error: ${apiErrorResponse.message()} (Code: ${apiErrorResponse.statusCode.code})")
    }
}