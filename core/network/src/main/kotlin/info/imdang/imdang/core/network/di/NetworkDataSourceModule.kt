package info.imdang.imdang.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.imdang.imdang.core.data.datasource.remote.AuthRemoteDataSource
import info.imdang.imdang.core.data.datasource.remote.MemberRemoteDataSource
import info.imdang.imdang.core.data.datasource.remote.TermRemoteDataSource
import info.imdang.imdang.core.network.impl.AuthRemoteDataSourceImpl
import info.imdang.imdang.core.network.impl.MemberRemoteDataSourceImpl
import info.imdang.imdang.core.network.impl.TermRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindMemberRemoteDataSource(memberRemoteDataSourceImpl: MemberRemoteDataSourceImpl): MemberRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindTermRemoteDataSource(termRemoteDataSourceImpl: TermRemoteDataSourceImpl): TermRemoteDataSource
}