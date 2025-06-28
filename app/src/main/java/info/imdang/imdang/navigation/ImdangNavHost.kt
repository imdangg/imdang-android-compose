package info.imdang.imdang.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import info.imdang.imdang.navigation.navGraph.HomeRoute
import info.imdang.imdang.navigation.navGraph.homeSection
import info.imdang.imdang.navigation.navGraph.insightScreen
import info.imdang.imdang.navigation.navGraph.myPageScreen
import info.imdang.imdang.navigation.navGraph.navigateToInsightDetail
import info.imdang.imdang.navigation.navGraph.navigateToMyPage
import info.imdang.imdang.navigation.navGraph.navigateToSearch
import info.imdang.imdang.navigation.navGraph.navigateToServiceInfo
import info.imdang.imdang.navigation.navGraph.navigateToServicePolicy
import info.imdang.imdang.navigation.navGraph.navigateToStorageDetail
import info.imdang.imdang.navigation.navGraph.searchScreen
import info.imdang.imdang.navigation.navGraph.serviceInfoScreen
import info.imdang.imdang.navigation.navGraph.servicePolicyScreen
import info.imdang.imdang.navigation.navGraph.storageDetailScreen
import info.imdang.imdang.navigation.navGraph.storageSection
import info.imdang.imdang.navigation.navGraph.writeSection

@Composable
fun ImdangNavHost(
    appState: ImdangAppSate,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        // -- home --
        homeSection(
            onInsightClick = navController::navigateToInsightDetail,
            onMyPageClick = navController::navigateToMyPage,
            onSearchClick = navController::navigateToSearch
        )

        // -- home 내부 --
        searchScreen(
            onBackClick = navController::popBackStack
        )

        insightScreen(
            onBackClick = navController::popBackStack
        )

        myPageScreen(
            onBackClick = navController::popBackStack,
            onServiceInfoClick = navController::navigateToServiceInfo,
            onPolicyClick = navController::navigateToServicePolicy
        )

        serviceInfoScreen(
            onBackClick = navController::popBackStack
        )
        servicePolicyScreen(
            onBackClick = navController::popBackStack
        )

        //-- write --
        writeSection(
            onBackClick = navController::popBackStack,
        )

        //-- storage --
        storageSection (onInsightClick = navController::navigateToStorageDetail)

        storageDetailScreen(
            onBackClick = navController::popBackStack,
        )
    }
}