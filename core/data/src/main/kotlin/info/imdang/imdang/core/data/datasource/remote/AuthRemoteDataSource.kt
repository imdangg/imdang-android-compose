package info.imdang.imdang.core.data.datasource.remote

import com.skydoves.sandwich.ApiResponse
import info.imdang.imdang.core.data.datasource.model.LoginEntity
import info.imdang.imdang.core.data.datasource.model.LoginRequestEntity

interface AuthRemoteDataSource {
    suspend fun getLogin(loginRequestEntity: LoginRequestEntity): ApiResponse<LoginEntity>
}