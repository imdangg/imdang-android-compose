package info.imdang.imdang.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.imdang.imdang.core.network.service.AuthService
import info.imdang.imdang.core.network.service.MemberService
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {
    @Provides
    @Singleton
    fun bindAuthService(
        @Named("imdang") retrofit: Retrofit
    ): AuthService = retrofit.create()

    @Provides
    @Singleton
    fun bindMemberService(
        @Named("imdang") retrofit: Retrofit
    ): MemberService = retrofit.create()
}