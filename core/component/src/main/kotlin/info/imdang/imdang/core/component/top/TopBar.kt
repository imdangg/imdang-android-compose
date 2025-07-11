package info.imdang.imdang.core.component.top

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import info.imdang.core.component.R
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.Gray25
import info.imdang.imdang.core.component.theme.Gray500
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.ImdangPreview

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    onClickedNotification: () -> Unit,
    onClickedProfile: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(Gray25)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = Gray100,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
            .padding(start = 20.dp, end = 20.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 탭 영역
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                tabs.forEach { tab ->
                    val isSelected = tab == selectedTab
                    Text(
                        text = tab,
                        style = topBarNavigationTextStyle,
                        color = if (isSelected) Gray900 else Gray500,
                        modifier = Modifier
                            .clickable { onTabSelected(tab) }
                    )
                }
            }

            // 아이콘 영역
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onClickedNotification) {
                    Icon(
                        painter = painterResource(R.drawable.alarm),
                        contentDescription = "Alarm",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }

                IconButton(onClick = onClickedProfile) {
                    Icon(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = "Profile",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }
        }
    }
}

@ImdangPreview
@Composable
fun TopBarPreview() {
    var selectedTab by remember { mutableStateOf("탐색") }

    TopBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp),
        tabs = listOf("탐색", "교환소"),
        selectedTab = selectedTab,
        onTabSelected = { selectedTab = it },
        onClickedNotification = {},
        onClickedProfile = {}
    )
}