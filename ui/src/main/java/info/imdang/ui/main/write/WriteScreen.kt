package info.imdang.ui.main.write

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WriteScreen(onBackClick: () -> Unit) {
    Column {
        Button(onClick = { onBackClick() }) { Text("BackStack") }
        Text("WriteScreen")
    }
}