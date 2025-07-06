package info.imdang.imdang.core.component.bottombar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import info.imdang.core.component.R
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.Gray500
import info.imdang.imdang.core.component.theme.Gray800
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.ImdangPreview

@Composable
fun ImdangNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .drawBehind {
                drawLine(
                    color = Gray100,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1f
                )
            },
        horizontalArrangement = Arrangement.spacedBy(60.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
fun NavigationDefaultItem(
    @DrawableRes iconId: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val color = if (isSelected) Gray900 else Gray500
        Icon(
            painter = painterResource(iconId),
            contentDescription = label,
            tint = color,
            modifier = Modifier
                .size(24.dp)
                .clickable { onClick() }
        )

        Text(
            text = label,
            style = bottomBarTextStyle,
            color = color
        )
    }
}

@Composable
fun NavigationCircleItem(
    @DrawableRes iconId: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val shadowColor = Color.Black.copy(alpha = 0.16f)
    val shadowOffsetY = 4.dp
    val shadowBlurRadius = 8.dp

    Box(
        modifier = Modifier
            .size(52.dp)
            .drawBehind {
                drawIntoCanvas { canvas ->
                    val paint = Paint().asFrameworkPaint().apply {
                        isAntiAlias = true
                        color = android.graphics.Color.TRANSPARENT
                        setShadowLayer(
                            shadowBlurRadius.toPx(),
                            0f,
                            shadowOffsetY.toPx(),
                            shadowColor.toArgb()
                        )
                    }
                    val radius = size.minDimension / 2f
                    canvas.nativeCanvas.drawCircle(
                        center.x,
                        center.y,
                        radius,
                        paint
                    )
                }
            }
            .background(
                color = Gray800,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable { onClick() }
        )
    }
}

@ImdangPreview
@Composable
private fun ImdangBottomBarPreview() {
    ImdangNavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
    ) {
        val iconList = listOf(
            R.drawable.home,
            R.drawable.pen_fill,
            R.drawable.file
        )
        val labelList = listOf(
            "홈",
            "",
            "보관함"
        )

        NavigationDefaultItem(
            iconId = iconList[0],
            label = labelList[0],
            isSelected = true,
            onClick = {}
        )
        NavigationCircleItem(
            iconId = iconList[1],
            label = labelList[1],
            isSelected = false,
            onClick = {}
        )
        NavigationDefaultItem(
            iconId = iconList[2],
            label = labelList[2],
            isSelected = false,
            onClick = {}
        )
    }

}