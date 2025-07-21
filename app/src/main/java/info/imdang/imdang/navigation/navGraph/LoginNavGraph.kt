package info.imdang.imdang.navigation.navGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import info.imdang.ui.login.LoginRoute
import kotlinx.serialization.Serializable
import info.imdang.ui.onboarding.OnboardingRoute

@Serializable
data object LoginBaseRoute

@Serializable
data object LoginRoute

@Serializable
data object OnboardingRoute

fun NavController.navigationToLogin(navOptions: NavOptions) =
    navigate(route = LoginRoute, navOptions)

fun NavGraphBuilder.loginSection(
    additionalDestination: NavGraphBuilder.() -> Unit,
) {
    navigation<LoginBaseRoute>(startDestination = LoginRoute) {
        composable<LoginRoute> {
            LoginRoute()
        }
    }
}
// 최초 로그인(회원가입)의 경우 LoginRoute에서 navigateToOnboarding 해주세요
// 온보딩에서는 마지막 페이지에서 delay 후 navigationToHome 됩니다
fun NavController.navigateToOnboarding(navOptions: NavOptionsBuilder.() -> Unit = {}) = navigate(route = OnboardingRoute, navOptions)

fun NavGraphBuilder.onboardingScreen(
    onBoardingFinished: () -> Unit,
) {
    composable<OnboardingRoute> {
        OnboardingRoute(onOnboardingFinished = onBoardingFinished)
    }
}