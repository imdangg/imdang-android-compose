package info.imdang.imdang.core.data.datasource.local

import info.imdang.imdang.core.data.datasource.model.LoginEntity
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    val loginEntity: Flow<LoginEntity?>

    suspend fun setLoginEntity(loginEntity: LoginEntity)

    suspend fun clearLoginEntity()
}