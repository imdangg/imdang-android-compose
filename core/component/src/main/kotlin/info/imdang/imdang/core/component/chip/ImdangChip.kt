package info.imdang.imdang.core.component.chip

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import info.imdang.core.component.R
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.Gray500
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.imdang.core.component.theme.Orange500

@Composable
fun CustomMaterialChip(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes iconResId: Int? = null,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                text = text,
                style = chipTextStyle
            )
        },
        trailingIcon = if (iconResId != null) {
            {
                Icon(
                    painter = painterResource(iconResId),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(12.dp)
                )
            }
        } else null,
        shape = RoundedCornerShape(100),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            labelColor = Gray500,
            iconColor = Gray500,
            selectedContainerColor = Orange500,
            selectedLabelColor = Color.White,
            selectedTrailingIconColor = Color.White,
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            borderColor = Gray100,
        ),
        modifier = modifier
    )
}

@ImdangPreview
@Composable
fun CustomMaterialChipPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            CustomMaterialChip(
                text = "전체",
                isSelected = true,
                iconResId = null,
                onClick = {},
            )
            CustomMaterialChip(
                text = "전체",
                isSelected = true,
                iconResId = R.drawable.down,
                onClick = {},
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            CustomMaterialChip(
                text = "전체",
                isSelected = false,
                iconResId = null,
                onClick = {},
            )
            CustomMaterialChip(
                text = "전체",
                isSelected = false,
                iconResId = R.drawable.down,
                onClick = {},
            )
        }
    }
}