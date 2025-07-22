package info.imdang.ui.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import info.imdang.imdang.core.component.buttons.ButtonSize
import info.imdang.imdang.core.component.buttons.MainButton
import info.imdang.imdang.core.component.theme.Gray25
import info.imdang.imdang.core.component.theme.Gray700
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.ui.R
import info.imdang.core.component.R as ComponentR

@Composable
fun JoinCompletedRoute(
    onClickPreferenceButton: () -> Unit,
) {
    JoinCompletedScreen(
        onClickPreferenceButton = onClickPreferenceButton
    )
}

@Composable
internal fun JoinCompletedScreen(
    onClickPreferenceButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray25),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(1.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(64.dp),
                painter = painterResource(ComponentR.drawable.circle_check_orange),
                contentDescription = "",
                tint = Color.Unspecified
            )

            Text(
                modifier = Modifier
                    .padding(top = 24.dp),
                text = stringResource(R.string.join_completed_title),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Gray900
                )
            )

            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = stringResource(R.string.join_completed_description),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = Gray700,
                    textAlign = TextAlign.Center,
                )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 40.dp)
        ) {
            MainButton(
                onClick = onClickPreferenceButton,
                modifier = Modifier
                    .fillMaxWidth(),
                buttonSize = ButtonSize.L,
                text = stringResource(R.string.go_to_preference_setting),
                enabled = true
            )
            LeftAnchoredSpeechBubble(
                text = stringResource(R.string.join_completed_hint),
                modifier = Modifier
                    .offset(y = (-50).dp)
            )
        }
    }
}

@Composable
fun LeftAnchoredSpeechBubble(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    textColor: Color = Color.White
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Text(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.labelLarge
            )
        }


        Canvas(
            modifier = Modifier
                .padding(start = 28.dp)
                .size(width = 16.dp, height = 10.dp)
        ) {
            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width / 2f, size.height)
                lineTo(size.width, 0f)
                close()
            }
            drawPath(path, color = backgroundColor)
        }
    }
}

@ImdangPreview
@Composable
private fun JoinCompletedScreenPreview() {
    ImdangAppNewTheme {
        JoinCompletedScreen(
            onClickPreferenceButton = {}
        )
    }
}