package info.imdang.imdang.core.data.datasource.impl

import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import info.imdang.imdang.core.data.datasource.local.AuthLocalDataSource
import info.imdang.imdang.core.data.datasource.model.mapper.ErrorResponseMapper
import info.imdang.imdang.core.data.datasource.model.toData
import info.imdang.imdang.core.data.datasource.remote.AuthRemoteDataSource
import info.imdang.imdang.core.domain.model.LoginData
import info.imdang.imdang.core.domain.model.LoginRequestData
import info.imdang.imdang.core.domain.model.ReissueData
import info.imdang.imdang.core.domain.model.ReissueRequestData
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

    override fun postLogin(
        loginRequestData: LoginRequestData,
    ): Flow<LoginData> = flow {
        val response = authRemoteDataSource.postLogin(loginRequestData.toData())
        response.suspendOnSuccess {
            authLocalDataSource.setLoginEntity(data)
            emit(data.toDomain())
        }.onError {
            throw map(ErrorResponseMapper)
        }.onException {
            throw throwable
        }
    }

    override fun postReissue(
        reissueRequestData: ReissueRequestData
    ): Flow<ReissueData> = flow {
        val response = authRemoteDataSource.postReissue(reissueRequestData.toData())
        response.suspendOnSuccess {
            emit(data.toDomain())
        }.onError {
            throw map(ErrorResponseMapper)
        }.onException {
            throw throwable
        }
    }

    override fun getSavedLoginData(): Flow<LoginData?> =
        authLocalDataSource.loginEntity.map { it?.toDomain() }
}