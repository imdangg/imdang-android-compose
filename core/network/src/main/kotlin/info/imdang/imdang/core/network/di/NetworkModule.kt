package info.imdang.imdang.core.network.di

import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.imdang.core.network.BuildConfig
import info.imdang.imdang.core.network.interceptor.TokenInterceptor
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    @Named("imdang")
    fun okHttpCallFactory(
        tokenInterceptor: TokenInterceptor
    ): Call.Factory =
        OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .build()

    @Provides
    @Singleton
    @Named("imdang")
    fun providesRetrofit(
        networkJson: Json,
        @Named("imdang") okhttpCallFactory: dagger.Lazy<Call.Factory>,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.API_SERVER)
            .callFactory(okhttpCallFactory.get())
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()

}