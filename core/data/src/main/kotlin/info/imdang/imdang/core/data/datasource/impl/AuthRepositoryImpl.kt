package info.imdang.imdang.core.data.datasource.impl

import info.imdang.imdang.core.common.dataresource.DataResource
import info.imdang.imdang.core.data.datasource.bound.flowDataResource
import info.imdang.imdang.core.data.datasource.remote.AuthRemoteDataSource
import info.imdang.imdang.core.domain.model.LoginData
import info.imdang.imdang.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override fun getKakaoLogin(provider: String, token: String): Flow<DataResource<LoginData>> =
        flowDataResource { authRemoteDataSource.getKakaoLogin(provider, token).toDomain() }
}