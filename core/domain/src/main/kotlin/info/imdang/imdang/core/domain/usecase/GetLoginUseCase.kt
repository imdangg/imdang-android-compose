package info.imdang.imdang.core.domain.usecase

import info.imdang.imdang.core.domain.model.LoginData
import info.imdang.imdang.core.domain.model.LoginRequestData
import info.imdang.imdang.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(loginRequestData: LoginRequestData): Flow<LoginData> =
        authRepository.getLogin(loginRequestData)
}