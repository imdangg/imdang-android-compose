package info.imdang.imdang.core.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.imdang.core.component.theme.Orange50
import info.imdang.imdang.core.component.theme.Orange500

@Composable
fun StateChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = Orange50,
                shape = RoundedCornerShape(10000.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            style = chipTextStyle,
            color = Orange500
        )
    }
}

@ImdangPreview
@Composable
fun OrangeChipPreview() {
    StateChip(
        text = "1 / 4",
        modifier = Modifier
            .size(width = 44.dp, height = 28.dp)
    )
}