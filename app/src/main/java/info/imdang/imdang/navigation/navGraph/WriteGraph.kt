package info.imdang.imdang.navigation.navGraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import info.imdang.ui.main.write.WriteScreen
import kotlinx.serialization.Serializable

//  Write
@Serializable
data object WriteRoute

fun NavController.navigationToWrite(navOptions: NavOptions) =
    navigate(route = WriteRoute, navOptions)

fun NavGraphBuilder.writeSection(
    onBackClick: () -> Unit,
    //  onNextClick: (String) -> Unit,  작성 디자인 대기,
) {
    composable<WriteRoute> {
        WriteScreen()
    }
}
