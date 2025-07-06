package info.imdang.imdang.ui

import android.util.Log
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import info.imdang.imdang.core.component.bottombar.ImdangNavigationBar
import info.imdang.imdang.core.component.bottombar.NavigationCircleItem
import info.imdang.imdang.core.component.bottombar.NavigationDefaultItem
import info.imdang.imdang.navigation.ImdangNavHost
import info.imdang.imdang.navigation.TopLevelDestination
import kotlin.reflect.KClass


@Composable
fun ImdangApp(
    appState: ImdangAppSate,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    ImdangApp(
        appState = appState,
        snackbarHostState = snackbarHostState,
        windowAdaptiveInfo = windowAdaptiveInfo
    )
}

@Composable
internal fun ImdangApp(
    appState: ImdangAppSate,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // 현재 destination이 TopLevelDestination의 baseRoute 중 하나에 속하면 BottomBar 표시, 내부 상세페이지 바텀바 x
    val showBottomBar = TopLevelDestination.entries.any { topLevel ->
        currentDestination?.hierarchy?.any { destination ->
            destination.route?.contains(topLevel.baseRoute.simpleName ?: "") == true
        } == true
    }

    //라우트 로그
    val navController = appState.navController
    CurrentRouteLogger(navController = navController)

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (showBottomBar) {
                ImdangBottomBar(
                    appState = appState,
                    onDestinationClick = appState::navigateToTopLevelDestination
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier
    ) { innerPadding ->
        ImdangNavHost(
            appState = appState,
            modifier = Modifier.padding(innerPadding)
        )
    }

}

@Composable
fun ImdangBottomBar(
    appState: ImdangAppSate,
    onDestinationClick: (TopLevelDestination) -> Unit
) {
    val currentDestination = appState.currentDestination

    ImdangNavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(92.dp)

    ) {
        TopLevelDestination.entries.forEach { destination ->
            val selected = currentDestination.isRouteInHierarchy(destination.baseRoute)

            if (destination != TopLevelDestination.WRITE) {
                NavigationDefaultItem(
                    iconId = if (selected) destination.selectedIconId else destination.unselectedIconId,
                    label = stringResource(destination.iconTextId),
                    isSelected = selected,
                    onClick = { onDestinationClick(destination) }
                )
            } else {
                NavigationCircleItem(
                    iconId = if (selected) destination.selectedIconId else destination.unselectedIconId,
                    label = stringResource(destination.iconTextId),
                    isSelected = selected,
                    onClick = { onDestinationClick(destination) }
                )
            }
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false

@Composable
fun CurrentRouteLogger(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        Log.e("NavDebug", "Current route: $currentRoute")
        navBackStackEntry?.destination?.hierarchy?.forEach {
            Log.e("NavDebug", "Hierarchy route: ${it.route}")
        }
    }
}