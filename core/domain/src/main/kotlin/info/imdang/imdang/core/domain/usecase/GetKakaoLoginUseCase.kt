package info.imdang.imdang.core.domain.usecase

import info.imdang.imdang.core.domain.repository.AuthRepository
import javax.inject.Inject

class GetKakaoLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(provider: String, token: String) =
        authRepository.getKakaoLogin(provider, token)
}