package info.imdang.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import info.imdang.imdang.core.component.theme.Orange500
import info.imdang.ui.R

@Composable
fun ImdangSplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange500),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = "Splash Logo"
        )
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    ImdangSplashScreen()
}