package info.imdang.imdang.core.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import info.imdang.core.component.R
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme


@Composable
private fun Testfillicon(){
    ImdangAppNewTheme {
        Icon(
            painter = painterResource(R.drawable.circle_cancle_black),
            contentDescription = "check_fill",
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview
@Composable
private fun Previewfillicon(){
   Testfillicon()
}