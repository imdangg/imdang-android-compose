package info.imdang.imdang.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlin.reflect.KClass
import info.imdang.ui.R as UiR
import info.imdang.imdang.R
import info.imdang.imdang.navigation.navGraph.HomeBaseRoute
import info.imdang.imdang.navigation.navGraph.HomeRoute
import info.imdang.imdang.navigation.navGraph.StorageBaseRoute
import info.imdang.imdang.navigation.navGraph.StorageRoute
import info.imdang.imdang.navigation.navGraph.WriteBaseRoute
import info.imdang.imdang.navigation.navGraph.WriteRoute

enum class TopLevelDestination(
    @DrawableRes val selectedIconId: Int,
    @DrawableRes val unselectedIconId: Int,
    @StringRes val iconTextId : Int,
    @StringRes val titleTextId : Int,
    val route : KClass<*>,
    val baseRoute : KClass<*> = route
) {
    //todo icon 수정
    HOME(
        selectedIconId = R.drawable.ic_home_selected,
        unselectedIconId = R.drawable.ic_home_selected,
        iconTextId = UiR.string.top_home_title,
        titleTextId = R.string.app_name,
        route = HomeRoute::class,
        baseRoute = HomeBaseRoute::class
    ),
    WRITE(
        selectedIconId = R.drawable.ic_home_selected,
        unselectedIconId = R.drawable.ic_home_selected,
        iconTextId = UiR.string.top_write_title,
        titleTextId =UiR.string.top_write_title,
        route = WriteRoute::class,
        baseRoute = WriteBaseRoute::class
    ),
    STORAGE(
        selectedIconId = R.drawable.ic_home_selected,
        unselectedIconId = R.drawable.ic_home_selected,
        iconTextId = UiR.string.top_storage_title,
        titleTextId = UiR.string.top_storage_title,
        route = StorageRoute::class,
        baseRoute = StorageBaseRoute::class
    )

}