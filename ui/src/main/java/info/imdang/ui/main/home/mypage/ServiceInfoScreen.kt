package info.imdang.ui.main.home.mypage

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ServiceInfoScreen(
    onBackClick: () -> Unit
) {
    Button(onClick = { onBackClick() }) { Text("BackStack") }

    Text("ServiceInfoScreen")
}
