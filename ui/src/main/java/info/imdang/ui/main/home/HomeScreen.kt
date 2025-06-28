package info.imdang.ui.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onInsightClick: (String) -> Unit,
    onMyPageClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Column {
        Text("Home")
        Button(onClick = { onInsightClick("123") }) { Text("Go to Insight Detail") }
        Button(onClick = { onMyPageClick() }) { Text("Go to My Page") }
        Button(onClick = { onSearchClick() }) { Text("Go to Search") }
    }
}