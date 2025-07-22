package info.imdang.imdang.core.data.datasource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.imdang.imdang.core.data.datasource.impl.AuthRepositoryImpl
import info.imdang.imdang.core.data.datasource.impl.MemberRepositoryImpl
import info.imdang.imdang.core.domain.repository.AuthRepository
import info.imdang.imdang.core.domain.repository.MemberRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindMemberRepository(memberRepositoryImpl: MemberRepositoryImpl): MemberRepository
}