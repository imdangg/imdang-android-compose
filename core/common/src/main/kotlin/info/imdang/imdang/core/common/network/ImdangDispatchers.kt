package info.imdang.imdang.core.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val imdangDispatcher: ImdangDispatchers)

enum class ImdangDispatchers {
    Default,
    IO,
}
