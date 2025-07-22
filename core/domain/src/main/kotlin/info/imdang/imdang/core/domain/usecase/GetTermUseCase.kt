package info.imdang.imdang.core.domain.usecase

import info.imdang.imdang.core.domain.model.TermData
import info.imdang.imdang.core.domain.repository.TermRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTermUseCase @Inject constructor(
    private val termRepository: TermRepository
) {
    operator fun invoke(): Flow<List<TermData>> =
        termRepository.getTerms()
}