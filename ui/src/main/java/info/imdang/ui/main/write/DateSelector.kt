package info.imdang.ui.main.write

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import info.imdang.imdang.core.component.theme.GrayScale100
import info.imdang.imdang.core.component.theme.GrayScale600
import info.imdang.imdang.core.component.theme.GrayScale900
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.White
import info.imdang.core.component.R as ComponentR

@Composable
internal fun DateSelector(
    modifier: Modifier = Modifier,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val targetHeight by animateDpAsState(
        targetValue = if (isExpanded) 325.dp else 52.dp,
        animationSpec = tween(durationMillis = 300)
    )
    val radius = if (isExpanded) 13.dp else 8.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(targetHeight)
            .background(White, RoundedCornerShape(radius))
            .border(
                BorderStroke(
                    1.dp,
                    if (isExpanded) Color.Transparent else GrayScale100
                ),
                shape = RoundedCornerShape(radius)
            )
            .clickable { isExpanded = !isExpanded }
    ) {
        if (!isExpanded) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "2025.06.22",
                    style = MaterialTheme.typography.labelSmall.copy(GrayScale900)
                )

                Icon(
                    painter = painterResource(ComponentR.drawable.celendar),
                    contentDescription = "celendar",
                    tint = GrayScale600,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        } else {

        }
    }
}

@Preview
@Composable
private fun DateSelectorPreview() {
    ImdangAppNewTheme {
        DateSelector()
    }
}