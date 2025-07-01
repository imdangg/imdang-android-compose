package info.imdang.imdang.navigation.navGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import info.imdang.ui.main.storage.StorageDetailScreen
import info.imdang.ui.main.storage.StorageScreen
import kotlinx.serialization.Serializable

// Storage
@Serializable
data object StorageBaseRoute

@Serializable
data object StorageRoute

@Serializable
data class StorageDetailRoute(val insightId: String)


fun NavController.navigationToStorage(navOptions: NavOptions) =
    navigate(route = StorageRoute, navOptions)


fun NavGraphBuilder.storageSection(
    onInsightClick: (String) -> Unit,
    additionalDestination: NavGraphBuilder.() -> Unit,
) {
    navigation<StorageBaseRoute>(startDestination = StorageRoute) {
        composable<StorageRoute> {
            StorageScreen(onInsightClick)
        }
    }
    additionalDestination()
}

fun NavController.navigateToStorageDetail(
    insightId: String, navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route = StorageDetailRoute(insightId)) {
        navOptions()
    }
}

fun NavGraphBuilder.storageDetailScreen(
    onBackClick: () -> Unit
) {
    composable<StorageDetailRoute> {
        StorageDetailScreen(onBackClick)
    }
}