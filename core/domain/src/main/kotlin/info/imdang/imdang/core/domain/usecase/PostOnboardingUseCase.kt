package info.imdang.imdang.core.domain.usecase

import info.imdang.imdang.core.domain.model.OnboardingRequestData
import info.imdang.imdang.core.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostOnboardingUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    operator fun invoke(onboardingRequestData: OnboardingRequestData): Flow<Boolean> =
    memberRepository.postOnboarding(onboardingRequestData)
}