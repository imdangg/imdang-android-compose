package info.imdang.imdang.core.network.impl

import info.imdang.core.network.BuildConfig
import info.imdang.imdang.core.data.datasource.remote.AuthRemoteDataSource
import info.imdang.imdang.core.network.model.LoginResponse
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

private interface RetrofitAuthNetworkApi {
    @POST("login")
    suspend fun postLogin(
        @Body provider: String,
        @Body token: String,
    ): LoginResponse
}

@Singleton
internal class AuthRemoteDataSourceImpl @Inject constructor(
    networkJson: Json,
    @Named("imdang") okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : AuthRemoteDataSource {
    private val networkApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_SERVER)
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitAuthNetworkApi::class.java)

    override suspend fun getKakaoLogin(provider: String, token: String) =
        networkApi.postLogin(provider, token).toData()
}