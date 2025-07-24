package info.imdang.imdang.core.domain.usecase

import info.imdang.imdang.core.domain.repository.TermRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostTermsAgreeUseCase @Inject constructor(
    private val termRepository: TermRepository
) {
    operator fun invoke(termsIds: List<Int>): Flow<Unit> =
        termRepository.postTermsAgree(termsIds)
}