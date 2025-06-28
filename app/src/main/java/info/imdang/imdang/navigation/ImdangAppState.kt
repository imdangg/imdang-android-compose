package info.imdang.imdang.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import info.imdang.imdang.navigation.navGraph.navigationToHome
import info.imdang.imdang.navigation.navGraph.navigationToStorage
import info.imdang.imdang.navigation.navGraph.navigationToWrite

@Composable
fun rememberImdangAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
) :ImdangAppSate {
    return remember(
        navController,
        coroutineScope,
    ) {
        ImdangAppSate(
            navController = navController,
            coroutineScope = coroutineScope,
        )
    }
}

@Stable
class ImdangAppSate(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(route = topLevelDestination.route) == true
            }
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigationToHome(topLevelNavOptions)
            TopLevelDestination.WRITE -> navController.navigationToWrite(topLevelNavOptions)
            TopLevelDestination.STORAGE -> navController.navigationToStorage(topLevelNavOptions)
        }
    }
}