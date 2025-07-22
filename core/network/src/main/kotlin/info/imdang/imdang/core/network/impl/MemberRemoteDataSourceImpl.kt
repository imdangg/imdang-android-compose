package info.imdang.imdang.core.network.impl

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mapSuccess
import info.imdang.imdang.core.data.datasource.model.JoinRequestEntity
import info.imdang.imdang.core.data.datasource.remote.MemberRemoteDataSource
import info.imdang.imdang.core.network.model.toRemote
import info.imdang.imdang.core.network.service.MemberService
import javax.inject.Inject

internal class MemberRemoteDataSourceImpl @Inject constructor(
    private val memberService: MemberService
) : MemberRemoteDataSource {

    override suspend fun putJoin(joinRequestEntity: JoinRequestEntity): ApiResponse<Boolean> =
        memberService.putJoin(joinRequestEntity.toRemote())
            .mapSuccess { data!! }
}