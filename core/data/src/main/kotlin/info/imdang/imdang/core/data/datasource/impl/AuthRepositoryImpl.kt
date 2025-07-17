package info.imdang.imdang.core.data.datasource.impl

import info.imdang.imdang.core.common.dataresource.DataResource
import info.imdang.imdang.core.data.datasource.bound.flowDataResource
import info.imdang.imdang.core.data.datasource.local.AuthLocalDataSource
import info.imdang.imdang.core.data.datasource.remote.AuthRemoteDataSource
import info.imdang.imdang.core.domain.model.LoginData
import info.imdang.imdang.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {
    override fun getKakaoLogin(provider: String, token: String): Flow<DataResource<LoginData>> =
        flowDataResource { authRemoteDataSource.getKakaoLogin(provider, token).toDomain() }

    override fun getSavedLoginData(): Flow<LoginData?> =
        authLocalDataSource.loginEntity.map { it?.toDomain() }
}