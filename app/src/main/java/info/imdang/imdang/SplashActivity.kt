package info.imdang.imdang

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import info.imdang.core.presentation.splash.SplashUiState
import info.imdang.core.presentation.splash.SplashViewModel
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.ui.splash.ImdangSplashScreen

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ImdangAppNewTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                LaunchedEffect(uiState) {
                    when (uiState) {
                        SplashUiState.Loading -> {}
                        SplashUiState.NavigateToHome -> {
                            navigateTo("home")
                        }

                        SplashUiState.NavigateToLogin -> {
                            navigateTo("login")
                        }
                    }
                }

                ImdangSplashScreen()
            }
        }
    }

    private fun navigateTo(startDest: String) {
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("start_destination", startDest)
        })
    }
}