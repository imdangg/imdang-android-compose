package info.imdang.imdang.core.domain.repository

import info.imdang.imdang.core.common.dataresource.DataResource
import info.imdang.imdang.core.domain.model.LoginData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun getKakaoLogin(provider: String, token: String): Flow<DataResource<LoginData>>
}