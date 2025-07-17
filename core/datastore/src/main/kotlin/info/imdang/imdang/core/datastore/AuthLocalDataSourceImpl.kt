package info.imdang.imdang.core.datastore

import androidx.datastore.core.DataStore
import info.imdang.imdang.core.data.datasource.local.AuthLocalDataSource
import info.imdang.imdang.core.data.datasource.model.LoginEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val authPreferences: DataStore<AuthPreferences>
) : AuthLocalDataSource {
    override val loginEntity = authPreferences.data
        .map { preferences ->
            LoginEntity(
                memberId = preferences.memberId,
                isJoined = preferences.isJoined,
                accessToken = preferences.accessToken,
                refreshToken = preferences.refreshToken,
            )
        }

    override suspend fun setLoginEntity(loginEntity: LoginEntity) {
        authPreferences.updateData {
            it.copy {
                this.memberId = loginEntity.memberId
                this.isJoined = loginEntity.isJoined
                this.accessToken = loginEntity.accessToken
                this.refreshToken = loginEntity.refreshToken
            }
        }
    }

}