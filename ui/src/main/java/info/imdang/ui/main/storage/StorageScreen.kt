package info.imdang.ui.main.storage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable fun StorageScreen(onInsightClick: (String) -> Unit) {
    Column {
        Text("Storage")
        Button(onClick = { onInsightClick("999") }) { Text("Go to Storage Detail") }
    }
}