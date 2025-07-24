package info.imdang.imdang.core.domain.usecase

import info.imdang.imdang.core.domain.model.ReissueData
import info.imdang.imdang.core.domain.model.ReissueRequestData
import info.imdang.imdang.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostReissueUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(reissueRequestData: ReissueRequestData): Flow<ReissueData> =
        authRepository.postReissue(reissueRequestData)
}