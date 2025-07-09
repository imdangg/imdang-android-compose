package info.imdang.imdang.core.component.radiobutton

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.imdang.core.component.theme.Orange500

@Composable
fun ImdangRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val innerColor = if (selected) Orange500 else Gray100

    Box(
        modifier = modifier
            .size(24.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Gray100,
                style = Stroke(width = 2.dp.toPx())
            )
        }

        Box(
            modifier = Modifier
                .size(10.dp)
                .background(innerColor, shape = CircleShape)
        )

    }
}

@ImdangPreview
@Composable
fun ImdangRadioButtonPreview() {
    val options = listOf("radio1", "radio2", "radio3")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Row(
        modifier = Modifier.selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        options.forEach { option ->
            ImdangRadioButton(
                selected = option == selectedOption,
                onClick = { selectedOption = option }
            )
        }
    }
}