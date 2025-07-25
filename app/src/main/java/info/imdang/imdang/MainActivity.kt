package info.imdang.imdang

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.navigation.navGraph.HomeBaseRoute
import info.imdang.imdang.navigation.navGraph.LoginBaseRoute
import info.imdang.imdang.ui.ImdangApp
import info.imdang.imdang.ui.rememberImdangAppState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            )
        )

        val startDestination = when (intent.getStringExtra("start_destination")) {
            "home" -> HomeBaseRoute::class
            else -> LoginBaseRoute::class
        }

        setContent {
            val appState = rememberImdangAppState()

            ImdangAppNewTheme {
                ImdangApp(
                    appState = appState,
                    startDestination = startDestination,
                )
            }
        }
    }

}

