package info.imdang.ui.main.home.insight

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun InsightDetailScreen(onBackClick :()->Unit) {
    Button(onClick = { onBackClick() }) { Text("BackStack") }
    Text("Insight Detail")

}
