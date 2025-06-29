package info.imdang.imdang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.ui.rememberImdangAppState
import info.imdang.imdang.ui.ImdangApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val appState = rememberImdangAppState()

            ImdangAppNewTheme {
                    ImdangApp(appState = appState)
                }
            }
        }

}

