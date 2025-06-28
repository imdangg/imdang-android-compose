package info.imdang.ui.main.home.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MyPageScreen(
    onBackClick: () -> Unit,
    onServiceInfoClick: () -> Unit,
    onPolicyClick: () -> Unit
) {
    Column {
        Button(onClick = { onBackClick() }) { Text("BackStack") }
        Button(onClick = { onServiceInfoClick() }) { Text("onServiceInfoClick") }
        Button(onClick = { onPolicyClick() }) { Text("onPolicyClick") }

        Text("My Page")
    }
}
