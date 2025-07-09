package info.imdang.imdang.core.component.top

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import info.imdang.core.component.R
import info.imdang.imdang.core.component.chip.StateChip
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.ImdangPreview

@Composable
fun TopTitleBar(
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int,
    text: String,
    percent: Int? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = Gray900,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                style = topTitleBarTextStyle,
                color = Gray900
            )
        }

        percent?.let {
            StateChip(
                text = "$it%",
                modifier = Modifier
                    .size(width = 47.dp, height = 28.dp)
            )
        }
    }
}

@ImdangPreview
@Composable
fun TopTitleBarPreview() {
    TopTitleBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp),
        iconResId = R.drawable.back,
        text = "인사이트 작성",
        percent = 80,
    )
}