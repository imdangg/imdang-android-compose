package info.imdang.imdang.core.domain.repository

import info.imdang.imdang.core.domain.model.LoginData
import info.imdang.imdang.core.domain.model.LoginRequestData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun getLogin(loginRequestData: LoginRequestData): Flow<LoginData>

    fun getSavedLoginData(): Flow<LoginData?>
}