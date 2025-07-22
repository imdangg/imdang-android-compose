package info.imdang.imdang.core.domain.repository

import info.imdang.imdang.core.domain.model.JoinRequestData
import kotlinx.coroutines.flow.Flow

interface MemberRepository {
    fun postLogin(joinRequestData: JoinRequestData): Flow<Boolean>
}