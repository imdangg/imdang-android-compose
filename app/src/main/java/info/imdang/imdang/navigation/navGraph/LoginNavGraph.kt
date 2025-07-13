package info.imdang.imdang.navigation.navGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import info.imdang.ui.login.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
data object LoginBaseRoute

@Serializable
data object LoginRoute

fun NavController.navigationToLogin(navOptions: NavOptions) =
    navigate(route = LoginRoute, navOptions)

fun NavGraphBuilder.loginSection(

) {
    navigation<LoginBaseRoute>(startDestination = LoginRoute) {
        composable<LoginRoute> {
            LoginRoute()
        }
    }
}