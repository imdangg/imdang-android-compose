package info.imdang.imdang.core.data.datasource.remote

import com.skydoves.sandwich.ApiResponse
import info.imdang.imdang.core.data.datasource.model.JoinRequestEntity
import info.imdang.imdang.core.data.datasource.model.OnboardingRequestEntity

interface MemberRemoteDataSource {
    suspend fun putJoin(joinRequestEntity: JoinRequestEntity): ApiResponse<Boolean>

    suspend fun postOnboarding(onboardingEntity: OnboardingRequestEntity) : ApiResponse<Boolean>
}