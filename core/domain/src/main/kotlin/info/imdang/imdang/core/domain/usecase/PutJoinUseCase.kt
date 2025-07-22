package info.imdang.imdang.core.domain.usecase

import info.imdang.imdang.core.domain.model.JoinRequestData
import info.imdang.imdang.core.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PutJoinUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    operator fun invoke(joinRequestData: JoinRequestData): Flow<Boolean> =
        memberRepository.putJoin(joinRequestData)
}