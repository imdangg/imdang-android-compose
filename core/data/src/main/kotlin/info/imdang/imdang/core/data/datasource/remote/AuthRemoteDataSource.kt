package info.imdang.imdang.core.data.datasource.remote

import info.imdang.imdang.core.data.datasource.model.LoginEntity

interface AuthRemoteDataSource {
    suspend fun getKakaoLogin(provider: String, token: String): LoginEntity
}