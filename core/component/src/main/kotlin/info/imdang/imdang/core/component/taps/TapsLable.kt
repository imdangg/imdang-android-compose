package info.imdang.imdang.core.component.taps

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import info.imdang.imdang.core.component.theme.Gray500
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.ImdangPreview

@Composable
fun CenteredTabRow(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.Bottom
    ) {
        tabs.forEachIndexed { index, title ->
            TabLabel(
                text = title,
                isSelected = index == selectedIndex,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onTabSelected(index) }
            )
        }
    }
}

@Composable
fun ScrollableTabRow(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = modifier
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.Bottom,
       contentPadding = PaddingValues(end = 20.dp)
    ) {
        items(
            items = tabs,
            key = { it }
        ) { title ->
            val index = tabs.indexOf(title)
            TabLabel(
                text = title,
                isSelected = index == selectedIndex,
                modifier = Modifier
                    .clickable { onTabSelected(index) }
            )
        }
    }
}

@Composable
internal fun TabLabel(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val textColor = if (isSelected) Gray900 else Gray500

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .drawBehind {
                if (isSelected) {
                    val strokeWidth = 2.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = Gray900,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                }
            }
    ) {
        Text(
            text = text,
            style = tapsLableTextStyle,
            color = textColor,
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@ImdangPreview
@Composable
fun CenteredTabRowPreview() {
    val tabs = listOf("Text Area", "Text Area")
    var selectedTab by remember { mutableStateOf(0) }

    CenteredTabRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        tabs = tabs,
        selectedIndex = selectedTab,
        onTabSelected = { selectedTab = it }
    )
}

@ImdangPreview
@Composable
fun ScrollableTabRowPreview() {
    val tabs = List(6) { "Text Area$it" }
    var selectedTab by remember { mutableStateOf(0) }

    ScrollableTabRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        tabs = tabs,
        selectedIndex = selectedTab,
        onTabSelected = { selectedTab = it }
    )
}