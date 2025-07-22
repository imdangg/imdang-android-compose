package info.imdang.imdang.core.data.datasource.impl

import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import info.imdang.imdang.core.data.datasource.local.AuthLocalDataSource
import info.imdang.imdang.core.data.datasource.model.toData
import info.imdang.imdang.core.data.datasource.remote.AuthRemoteDataSource
import info.imdang.imdang.core.domain.model.LoginData
import info.imdang.imdang.core.domain.model.LoginRequestData
import info.imdang.imdang.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {

    private val tag = AuthRepositoryImpl::class.simpleName

    override fun getLogin(
        loginRequestData: LoginRequestData,
    ): Flow<LoginData> = flow {
        val response = authRemoteDataSource.getLogin(loginRequestData.toData())
        response.suspendOnSuccess {
            authLocalDataSource.setLoginEntity(data)
            emit(data.toDomain())
        }.onError {

        }.onFailure {

        }
    }

    override fun getSavedLoginData(): Flow<LoginData?> =
        authLocalDataSource.loginEntity.map { it?.toDomain() }
}