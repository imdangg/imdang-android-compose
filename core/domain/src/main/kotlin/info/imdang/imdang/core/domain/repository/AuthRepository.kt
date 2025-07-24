package info.imdang.imdang.core.domain.repository

import info.imdang.imdang.core.domain.model.LoginData
import info.imdang.imdang.core.domain.model.LoginRequestData
import info.imdang.imdang.core.domain.model.ReissueData
import info.imdang.imdang.core.domain.model.ReissueRequestData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun getLogin(loginRequestData: LoginRequestData): Flow<LoginData>

    fun postReissue(reissueRequestData: ReissueRequestData): Flow<ReissueData>

    fun getSavedLoginData(): Flow<LoginData?>
}