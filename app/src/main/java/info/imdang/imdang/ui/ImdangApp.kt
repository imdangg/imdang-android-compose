package info.imdang.imdang.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Short
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import info.imdang.imdang.core.component.bottombar.ImdangNavigationBar
import info.imdang.imdang.core.component.bottombar.NavigationCircleItem
import info.imdang.imdang.core.component.bottombar.NavigationDefaultItem
import info.imdang.imdang.navigation.ImdangNavHost
import info.imdang.imdang.navigation.TopLevelDestination
import info.imdang.imdang.navigation.navGraph.WriteBaseRoute
import kotlin.reflect.KClass


@Composable
fun ImdangApp(
    appState: ImdangAppSate,
    startDestination: KClass<*>,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    ImdangApp(
        appState = appState,
        startDestination = startDestination,
        snackbarHostState = snackbarHostState,
        windowAdaptiveInfo = windowAdaptiveInfo
    )
}

@Composable
internal fun ImdangApp(
    appState: ImdangAppSate,
    startDestination: KClass<*>,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // 현재 destination이 TopLevelDestination의 baseRoute 중 하나에 속하면 BottomBar 표시, 내부 상세페이지 바텀바 x
    // WriteBaseRoute를 제외하고 바텀바 표시 (더 좋은 방법이 있을것같다. 추후 수정 필요)
    val showBottomBar = TopLevelDestination.entries.any { topLevel ->
        topLevel.baseRoute != WriteBaseRoute::class &&
                currentDestination?.hierarchy?.any { destination ->
                    destination.route?.contains(topLevel.baseRoute.simpleName ?: "") == true
                } == true
    }

    Scaffold(
        containerColor = Color.Transparent,
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
    ) { paddingValues ->

        ImdangNavHost(
            appState = appState,
            startDestination = startDestination,
            modifier = Modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
                .statusBarsPadding()
                .navigationBarsPadding(),
            onShowSnackbar = { message, action ->
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = action,
                    duration = Short,
                ) == ActionPerformed
            }
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


