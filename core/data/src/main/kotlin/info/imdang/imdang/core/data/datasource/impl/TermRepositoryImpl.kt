package info.imdang.imdang.core.data.datasource.impl

import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import info.imdang.imdang.core.data.datasource.model.mapper.ErrorResponseMapper
import info.imdang.imdang.core.data.datasource.remote.TermRemoteDataSource
import info.imdang.imdang.core.domain.model.TermData
import info.imdang.imdang.core.domain.repository.TermRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TermRepositoryImpl @Inject constructor(
    private val termRemoteDataSource: TermRemoteDataSource
) : TermRepository {

    override fun getTerms(): Flow<List<TermData>> = flow {
        val response = termRemoteDataSource.getTerms()
        response.suspendOnSuccess {
            emit(data.map { it.toDomain() })
        }.onError {
            throw map(ErrorResponseMapper)
        }.onException {
            throw throwable
        }
    }

    override fun postTermsAgree(termsIds: List<Int>): Flow<Unit> = flow {
        val response = termRemoteDataSource.postTermsAgree(termsIds)
        response.suspendOnSuccess {
            emit(data)
        }.onError {
            throw map(ErrorResponseMapper)
        }.onException {
            throw throwable
        }
    }
}