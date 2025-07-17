package info.imdang.imdang.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import info.imdang.imdang.core.common.network.Dispatcher
import info.imdang.imdang.core.common.network.ImdangDispatchers.IO
import info.imdang.imdang.core.common.network.di.ApplicationScope
import info.imdang.imdang.core.datastore.AuthPreferences
import info.imdang.imdang.core.datastore.AuthPreferencesSerializer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

    @Provides
    @Singleton
    internal fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        authPreferencesSerializer: AuthPreferencesSerializer,
    ): DataStore<AuthPreferences> =
        DataStoreFactory.create(
            serializer = authPreferencesSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
        ) {
            context.dataStoreFile("auth_preferences.pb")
        }
}