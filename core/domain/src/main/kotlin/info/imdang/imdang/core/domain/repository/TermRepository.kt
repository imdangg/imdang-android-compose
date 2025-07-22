package info.imdang.imdang.core.domain.repository

import info.imdang.imdang.core.domain.model.TermData
import kotlinx.coroutines.flow.Flow

interface TermRepository {
    fun getTerms(): Flow<List<TermData>>
}