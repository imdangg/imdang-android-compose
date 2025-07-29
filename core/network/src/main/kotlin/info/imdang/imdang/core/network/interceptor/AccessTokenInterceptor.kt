package info.imdang.imdang.core.network.interceptor

import info.imdang.imdang.core.data.datasource.local.AuthLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class AccessTokenInterceptor @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        val loginEntity = authLocalDataSource.loginEntity.first()
        val token = loginEntity?.accessToken

        val request = chain.request().newBuilder()
            .apply {
                if (!token.isNullOrBlank()) {
                    addHeader("Authorization", "Bearer $token")
                }
            }
            .build()

        chain.proceed(request)
    }
}
