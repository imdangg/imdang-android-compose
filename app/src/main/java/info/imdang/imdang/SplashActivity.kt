package info.imdang.imdang

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.ui.splash.ImdangSplashScreen

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ImdangAppNewTheme {
                ImdangSplashScreen {
                    Intent(this@SplashActivity,MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }.let { intent ->
                        startActivity(intent)
                    }
                }
            }
        }
    }
}