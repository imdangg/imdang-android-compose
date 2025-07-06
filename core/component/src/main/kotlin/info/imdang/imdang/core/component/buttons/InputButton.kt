package info.imdang.imdang.core.component.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.Gray400
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.imdang.core.component.theme.Orange50
import info.imdang.imdang.core.component.theme.Orange500
import info.imdang.imdang.core.component.theme.White
import info.imdang.imdang.core.component.theme.pretendardFont

@Composable
fun InputButton(
    onClick: () -> Unit,
    text: String,
    state: Boolean,
    modifier: Modifier,
    //    enabled : Boolean = true  // state가 enabled인가?
) {
    val borderColor = if (state) Orange500 else Gray100

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (state) Orange50 else White,
            contentColor = if (state) Orange500 else Gray400,
        ),
        border = BorderStroke(1.dp, borderColor),
//        enabled = state    // state가 enabled인가?
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = pretendardFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = (16 * 1.4).sp,
            )
        )
    }
}

@ImdangPreview
@Composable
fun InputButtonPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        InputButton(
            onClick = {},
            text = "Text",
            state = true,
            modifier = Modifier
                .size(width = 160.dp, height = 52.dp)
        )

        InputButton(
            onClick = {},
            text = "Text",
            state = false,
            modifier = Modifier
                .size(width = 160.dp, height = 52.dp)
        )
    }
}