package info.imdang.imdang.core.domain.repository

import info.imdang.imdang.core.domain.model.JoinRequestData
import info.imdang.imdang.core.domain.model.OnboardingRequestData
import kotlinx.coroutines.flow.Flow

interface MemberRepository {
    fun putJoin(joinRequestData: JoinRequestData): Flow<Boolean>
    fun postOnboarding(onboardingData: OnboardingRequestData) : Flow<Boolean>
}