package info.imdang.imdang.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import info.imdang.imdang.navigation.navGraph.homeSection
import info.imdang.imdang.navigation.navGraph.insightScreen
import info.imdang.imdang.navigation.navGraph.loginSection
import info.imdang.imdang.navigation.navGraph.myPageScreen
import info.imdang.imdang.navigation.navGraph.navigateToInsightDetail
import info.imdang.imdang.navigation.navGraph.navigateToMyPage
import info.imdang.imdang.navigation.navGraph.navigateToOnboarding
import info.imdang.imdang.navigation.navGraph.navigateToSearch
import info.imdang.imdang.navigation.navGraph.navigateToServiceInfo
import info.imdang.imdang.navigation.navGraph.navigateToServicePolicy
import info.imdang.imdang.navigation.navGraph.navigateToStorageDetail
import info.imdang.imdang.navigation.navGraph.navigationToBasicProfileInput
import info.imdang.imdang.navigation.navGraph.navigationToHome
import info.imdang.imdang.navigation.navGraph.navigationToJoinCompleted
import info.imdang.imdang.navigation.navGraph.onboardingScreen
import info.imdang.imdang.navigation.navGraph.searchScreen
import info.imdang.imdang.navigation.navGraph.serviceInfoScreen
import info.imdang.imdang.navigation.navGraph.servicePolicyScreen
import info.imdang.imdang.navigation.navGraph.storageDetailScreen
import info.imdang.imdang.navigation.navGraph.storageSection
import info.imdang.imdang.navigation.navGraph.writeSection
import info.imdang.imdang.ui.ImdangAppSate
import kotlin.reflect.KClass

@Composable
fun ImdangNavHost(
    appState: ImdangAppSate,
    startDestination: KClass<*>,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        // -- login --
        loginSection(
            navigateToHome = navController::navigationToHome,
            navigateToBasicProfileInput = navController::navigationToBasicProfileInput,
            onAgreeClick = navController::navigationToJoinCompleted,
            onClickPreferenceButton = {
                navController.navigateToOnboarding {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            },
            onBackClick = navController::popBackStack,
        ) {
            onboardingScreen(onBoardingFinished = navController::navigationToHome)
        }

        // -- home --
        homeSection(
            onInsightClick = navController::navigateToInsightDetail,
            onMyPageClick = navController::navigateToMyPage,
            onSearchClick = navController::navigateToSearch
        ) {
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

        }


        //-- write --
        writeSection(
            onBackClick = navController::popBackStack,
        ) {

        }

        //-- storage --
        storageSection(onInsightClick = navController::navigateToStorageDetail) {
            storageDetailScreen(
                onBackClick = navController::popBackStack,
            )
        }
    }
}