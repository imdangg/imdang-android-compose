package info.imdang.imdang.core.network.service

import com.skydoves.sandwich.ApiResponse
import info.imdang.imdang.core.network.model.TermResponse
import info.imdang.imdang.core.network.model.base.ApiResultResponse
import retrofit2.http.GET

internal interface TermService {
    @GET("terms")
    suspend fun getTerms(): ApiResponse<ApiResultResponse<List<TermResponse>>>
}