package info.imdang.imdang.core.data.datasource.remote

import com.skydoves.sandwich.ApiResponse
import info.imdang.imdang.core.data.datasource.model.JoinRequestEntity

interface MemberRemoteDataSource {
    suspend fun postJoin(joinRequestEntity: JoinRequestEntity): ApiResponse<Boolean>
}