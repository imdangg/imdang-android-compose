package info.imdang.imdang.core.network.service

import com.skydoves.sandwich.ApiResponse
import info.imdang.imdang.core.network.model.JoinRequest
import info.imdang.imdang.core.network.model.OnboardingRequest
import info.imdang.imdang.core.network.model.base.ApiResultResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

internal interface MemberService {
    @PUT("members/join")
    suspend fun putJoin(
        @Body joinRequest: JoinRequest,
    ): ApiResponse<ApiResultResponse<Boolean>>

    @POST("members/onboarding")
    suspend fun postOnboarding(
        @Body boardingRequest : OnboardingRequest,
    ) : ApiResponse<ApiResultResponse<Boolean>>
}