package info.imdang.imdang.navigation.navGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import info.imdang.ui.main.home.insight.InsightDetailScreen
import info.imdang.ui.main.home.HomeScreen
import info.imdang.ui.main.home.mypage.MyPageScreen
import info.imdang.ui.main.home.search.SearchScreen
import info.imdang.ui.main.home.mypage.ServiceInfoScreen
import info.imdang.ui.main.home.mypage.ServicePolicyScreen


// Home
@Serializable
data object HomeBaseRoute

@Serializable
data object HomeRoute

@Serializable
data class InsightDetailRoute(val id: String)

@Serializable
data object MyPageRoute

@Serializable
object ServiceInfoRoute

@Serializable
object ServicePolicyRoute

@Serializable
data object SearchRoute

fun NavController.navigationToHome(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

fun NavController.navigationToHome(
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(HomeRoute, navOptions)
}

fun NavGraphBuilder.homeSection(
    onInsightClick: (String) -> Unit,
    onMyPageClick: () -> Unit,
    onSearchClick: () -> Unit,
    additionalDestination: NavGraphBuilder.() -> Unit,
) {
    navigation<HomeBaseRoute>(startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(onInsightClick, onMyPageClick, onSearchClick)
        }
    }
    additionalDestination()
}


fun NavController.navigateToInsightDetail(
    insightId: String, navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route = InsightDetailRoute(insightId)) {
        navOptions()
    }
}

fun NavGraphBuilder.insightScreen(
    onBackClick: () -> Unit,
) {
    composable<InsightDetailRoute> {
        InsightDetailScreen(
            onBackClick = onBackClick
        )
    }
}

fun NavController.navigateToMyPage(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = MyPageRoute, navOptions)

fun NavGraphBuilder.myPageScreen(
    onBackClick: () -> Unit,
    onServiceInfoClick: () -> Unit,
    onPolicyClick: () -> Unit,
    additionalDestinations: NavGraphBuilder.() -> Unit = {}
) {
    composable<MyPageRoute> {
        MyPageScreen(
            onBackClick = onBackClick,
            onServiceInfoClick = onServiceInfoClick,
            onPolicyClick = onPolicyClick
        )
    }
    additionalDestinations()
}

fun NavController.navigateToServiceInfo(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = ServiceInfoRoute, navOptions)

fun NavController.navigateToServicePolicy(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = ServicePolicyRoute, navOptions)

fun NavGraphBuilder.serviceInfoScreen(
    onBackClick: () -> Unit,
) {
    composable<ServiceInfoRoute> {
        ServiceInfoScreen(
            onBackClick = onBackClick
        )
    }
}

fun NavGraphBuilder.servicePolicyScreen(
    onBackClick: () -> Unit,
) {
    composable<ServicePolicyRoute> {
        ServicePolicyScreen(
            onBackClick = onBackClick
        )
    }
}


fun NavController.navigateToSearch(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = SearchRoute, navOptions)

fun NavGraphBuilder.searchScreen(
    onBackClick: () -> Unit,
) {
    composable<SearchRoute> {
        SearchScreen(
            onBackClick = onBackClick
        )
    }
}


