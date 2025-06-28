package info.imdang.ui.main.home.search

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SearchScreen(onBackClick:()->Unit) {
    Button(onClick = { onBackClick() }) { Text("BackStack") }
    Text("Search") }
