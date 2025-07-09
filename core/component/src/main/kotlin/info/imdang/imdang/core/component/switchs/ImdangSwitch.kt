package info.imdang.imdang.core.component.switchs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.imdang.core.component.theme.Orange500

@Composable
fun ImdangSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    width: Dp = 32.dp,
    height: Dp = 18.dp
) {
    val defaultWidth = 52.dp
    val defaultHeight = 32.dp
    val scaleX = width.value / defaultWidth.value
    val scaleY = height.value / defaultHeight.value

    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = Modifier
            .size(
                width,
                height
            )
            .graphicsLayer(
                scaleX = scaleX,
                scaleY = scaleY
            ),
        thumbContent = {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(Color.White)
                    .clip(CircleShape)
            )
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            uncheckedThumbColor = Color.White,
            checkedTrackColor = Orange500,
            uncheckedTrackColor = Gray100,
            checkedBorderColor = Color.Transparent,
            uncheckedBorderColor = Color.Transparent
        )
    )
}

@ImdangPreview
@Composable
fun ImdangSwitchPreview() {
    var checked by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ImdangSwitch(
            checked = checked,
            onCheckedChange = { checked = it }
        )
        ImdangSwitch(
            checked = !checked,
            onCheckedChange = { checked = !it }
        )
    }
}