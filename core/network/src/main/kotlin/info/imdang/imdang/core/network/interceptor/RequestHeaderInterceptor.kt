package info.imdang.imdang.core.network.interceptor

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class RequestHeaderInterceptor @Inject constructor(
//    private val authLocalDataSource: AuthLocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
//        val accessToken = authLocalDataSource.getAccessToken()

        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
//            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        chain.proceed(newRequest)
    }
}