package info.imdang.imdang.navigation.navGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import info.imdang.ui.login.BasicProfileInputRoute
import info.imdang.ui.login.JoinCompletedRoute
import info.imdang.ui.login.LoginRoute
import info.imdang.ui.onboarding.OnboardingRoute
import kotlinx.serialization.Serializable

@Serializable
data object LoginBaseRoute

@Serializable
data object LoginRoute

@Serializable
data object BasicProfileInputRoute

@Serializable
data object OnboardingRoute

@Serializable
data object JoinCompletedRoute

fun NavController.navigationToLogin(navOptions: NavOptions) =
    navigate(route = LoginRoute, navOptions)

fun NavController.navigationToBasicProfileInput(
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(BasicProfileInputRoute, navOptions)
}

fun NavController.navigationToJoinCompleted(
    navOptions: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(JoinCompletedRoute, navOptions)
}

fun NavGraphBuilder.loginSection(
    navigateToHome: () -> Unit,
    navigateToBasicProfileInput: () -> Unit,
    onAgreeClick: () -> Unit,
    onClickPreferenceButton: () -> Unit,
    onBackClick: () -> Unit,
    additionalDestination: NavGraphBuilder.() -> Unit,
) {
    navigation<LoginBaseRoute>(startDestination = LoginRoute) {
        composable<LoginRoute> {
            LoginRoute(
                navigateToHome = navigateToHome,
                navigateToBasicProfileInput = navigateToBasicProfileInput
            )
        }

        composable<BasicProfileInputRoute> {
            BasicProfileInputRoute(
                onAgreeClick = onAgreeClick,
                onBackClick = onBackClick,
            )
        }

        composable<JoinCompletedRoute> {
            JoinCompletedRoute(
                onClickPreferenceButton = onClickPreferenceButton
            )
        }
    }
}

// 최초 로그인(회원가입)의 경우 LoginRoute에서 navigateToOnboarding 해주세요
// 온보딩에서는 마지막 페이지에서 delay 후 navigationToHome 됩니다
fun NavController.navigateToOnboarding(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = OnboardingRoute, navOptions)

fun NavGraphBuilder.onboardingScreen(
    onBoardingFinished: () -> Unit,
) {
    composable<OnboardingRoute> {
        OnboardingRoute(onOnboardingFinished = onBoardingFinished)
    }
}