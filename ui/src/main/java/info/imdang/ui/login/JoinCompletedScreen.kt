package info.imdang.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.ImdangPreview

@Composable
fun JoinCompletedRoute() {
    JoinCompletedScreen()
}

@Composable
internal fun JoinCompletedScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("JoinCompletedScreen")
    }
}

@ImdangPreview
@Composable
private fun JoinCompletedScreenPreview() {
    ImdangAppNewTheme {
        JoinCompletedScreen()
    }
}