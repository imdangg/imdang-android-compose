package info.imdang.imdang.core.domain.usecase

import info.imdang.imdang.core.domain.repository.AuthRepository
import javax.inject.Inject

class GetSavedLoginDataUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.getSavedLoginData()
}