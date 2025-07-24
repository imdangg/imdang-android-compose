package info.imdang.imdang.core.network.impl

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mapSuccess
import info.imdang.imdang.core.data.datasource.model.TermEntity
import info.imdang.imdang.core.data.datasource.remote.TermRemoteDataSource
import info.imdang.imdang.core.network.service.TermService
import javax.inject.Inject

internal class TermRemoteDataSourceImpl @Inject constructor(
    private val termService: TermService
) : TermRemoteDataSource {

    override suspend fun getTerms(): ApiResponse<List<TermEntity>> =
        termService.getTerms()
            .mapSuccess { data!!.map { it.toData() } }

    override suspend fun postTermsAgree(termsIds: List<Int>): ApiResponse<Unit> =
        termService.postTermsAgree(termsIds)
            .mapSuccess { data!! }
}