package info.imdang.imdang.core.network.service

import com.skydoves.sandwich.ApiResponse
import info.imdang.imdang.core.network.model.JoinRequest
import info.imdang.imdang.core.network.model.base.ApiResultResponse
import retrofit2.http.Body
import retrofit2.http.POST

internal interface MemberService {
    @POST("members/join")
    suspend fun postJoin(
        @Body joinRequest: JoinRequest,
    ): ApiResponse<ApiResultResponse<Boolean>>
}