package info.imdang.imdang.core.data.datasource.impl

import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import info.imdang.imdang.core.data.datasource.model.mapper.ErrorResponseMapper
import info.imdang.imdang.core.data.datasource.model.toData
import info.imdang.imdang.core.data.datasource.remote.MemberRemoteDataSource
import info.imdang.imdang.core.domain.model.JoinRequestData
import info.imdang.imdang.core.domain.model.OnboardingRequestData
import info.imdang.imdang.core.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberRemoteDataSource: MemberRemoteDataSource
) : MemberRepository {

    override fun putJoin(joinRequestData: JoinRequestData): Flow<Boolean> = flow {
        val response = memberRemoteDataSource.putJoin(joinRequestData.toData())
        response.suspendOnSuccess {
            emit(data)
        }.onError {
            throw map(ErrorResponseMapper)
        }.onException {
            throw throwable
        }
    }

    override fun postOnboarding(onboardingData: OnboardingRequestData): Flow<Boolean> =flow {
        val response = memberRemoteDataSource.postOnboarding(onboardingData.toData())
        response.suspendOnSuccess {
            emit(data)
        }.onError {
            throw map(ErrorResponseMapper)
        }.onException {
            throw throwable
        }
    }
}