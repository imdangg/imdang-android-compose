package info.imdang.imdang.core.component.textinput

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import info.imdang.core.component.R
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.Gray400
import info.imdang.imdang.core.component.theme.ImdangPreview

@Composable
fun SearchInputTop(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    onClickedBack: () -> Unit,
    onClickedDelete: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp, end = 4.dp, top = 12.dp),
            value = value,
            onValueChange = onValueChange,
            textStyle = searchInputTopTextStyle,
            placeholder = {
                Text(
                    text = placeholder,
                    style = searchInputTopTextStyle,
                    color = Gray400
                )
            },
            leadingIcon = {
                IconButton(onClick = onClickedBack) {
                    Icon(
                        painter = painterResource(R.drawable.back),
                        contentDescription = "Back Button",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = onClickedDelete) {
                        Icon(
                            painter = painterResource(R.drawable.circle_cancle),
                            contentDescription = "Delete Button",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                }
            },
            singleLine = true,
            colors = searchInputTopTextFieldColor()
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            thickness = 1.dp,
            color = Gray100
        )
    }
}

@ImdangPreview
@Composable
fun SearchInputTopPreview() {
    var text by remember { mutableStateOf("") }

    SearchInputTop(
        value = text,
        onValueChange = { text = it },
        placeholder = "플레이스 홀더",
        onClickedBack = {},
        onClickedDelete = {},
    )
}