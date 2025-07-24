package info.imdang.imdang.core.network.service

import com.skydoves.sandwich.ApiResponse
import info.imdang.imdang.core.network.model.TermResponse
import info.imdang.imdang.core.network.model.base.ApiResultResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface TermService {
    @GET("terms")
    suspend fun getTerms(): ApiResponse<ApiResultResponse<List<TermResponse>>>

    @POST("terms/agree")
    suspend fun postTermsAgree(
        @Query("termsIds") termsIds: List<Int>
    ): ApiResponse<ApiResultResponse<Unit>>
}