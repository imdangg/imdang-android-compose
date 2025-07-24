package info.imdang.imdang.core.data.datasource.remote

import com.skydoves.sandwich.ApiResponse
import info.imdang.imdang.core.data.datasource.model.TermEntity

interface TermRemoteDataSource {
    suspend fun getTerms(): ApiResponse<List<TermEntity>>

    suspend fun postTermsAgree(termsIds: List<Int>): ApiResponse<Unit>
}