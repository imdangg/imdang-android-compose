package info.imdang.imdang.core.network.service

import com.skydoves.sandwich.ApiResponse
import info.imdang.imdang.core.network.model.LoginRequest
import info.imdang.imdang.core.network.model.LoginResponse
import info.imdang.imdang.core.network.model.base.ApiResultResponse
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthService {
    @POST("login")
    suspend fun postLogin(
        @Body loginRequest: LoginRequest,
    ): ApiResponse<ApiResultResponse<LoginResponse>>
}