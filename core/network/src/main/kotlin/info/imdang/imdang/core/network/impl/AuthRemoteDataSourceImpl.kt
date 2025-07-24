package info.imdang.imdang.core.network.impl

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mapSuccess
import info.imdang.imdang.core.data.datasource.model.LoginEntity
import info.imdang.imdang.core.data.datasource.model.LoginRequestEntity
import info.imdang.imdang.core.data.datasource.model.ReissueEntity
import info.imdang.imdang.core.data.datasource.model.ReissueRequestEntity
import info.imdang.imdang.core.data.datasource.remote.AuthRemoteDataSource
import info.imdang.imdang.core.network.model.toRemote
import info.imdang.imdang.core.network.service.AuthService
import javax.inject.Inject

internal class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {

    override suspend fun getLogin(loginRequestEntity: LoginRequestEntity): ApiResponse<LoginEntity> =
        authService.postLogin(loginRequestEntity.toRemote())
            .mapSuccess { data!!.toData() }

    override suspend fun postReissue(reissueRequestEntity: ReissueRequestEntity): ApiResponse<ReissueEntity> =
        authService.postReissue(reissueRequestEntity.toRemote())
            .mapSuccess { data!!.toData() }
}