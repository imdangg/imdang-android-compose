package info.imdang.imdang.core.data.datasource.remote

import com.skydoves.sandwich.ApiResponse
import info.imdang.imdang.core.data.datasource.model.LoginEntity
import info.imdang.imdang.core.data.datasource.model.LoginRequestEntity
import info.imdang.imdang.core.data.datasource.model.ReissueEntity
import info.imdang.imdang.core.data.datasource.model.ReissueRequestEntity

interface AuthRemoteDataSource {
    suspend fun getLogin(loginRequestEntity: LoginRequestEntity): ApiResponse<LoginEntity>

    suspend fun postReissue(reissueRequestEntity: ReissueRequestEntity): ApiResponse<ReissueEntity>
}