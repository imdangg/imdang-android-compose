package info.imdang.ui.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.imdang.imdang.core.component.radiobutton.ImdangRadioButton
import info.imdang.imdang.core.component.theme.Black
import info.imdang.imdang.core.component.theme.FontBlack
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.Gray30
import info.imdang.imdang.core.component.theme.Gray700
import info.imdang.imdang.core.component.theme.Gray80
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.Orange300
import info.imdang.imdang.core.component.theme.Orange450
import info.imdang.imdang.core.component.theme.Orange50
import info.imdang.imdang.core.component.theme.Orange500
import info.imdang.imdang.core.component.theme.White
import info.imdang.ui.R

@Composable
fun PurposeButton(iconId: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.semantics { this.selected = isSelected },
        contentPadding = PaddingValues(
            horizontal = 65.5.dp,
            vertical = 48.dp
        ),
        colors = ButtonDefaults.buttonColors(
            contentColor = if (isSelected) Orange500 else Gray700,
            containerColor = if (isSelected) Orange50 else White
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, if (isSelected) Orange300 else Gray100)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = label,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, style = MaterialTheme.typography.titleSmall)
        }
    }
}


@Composable
fun RadioButtonItem(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Gray80)
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImdangRadioButton(
            modifier = Modifier.padding(start = 16.dp),
            selected = selected,
            onClick = { onClick() }
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(color = Gray900)
        )
    }
}


@Composable
fun OptText(
    text : String,
    isSelected : Boolean,
    onClick: () -> Unit,
    modifier : Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall.copy(
            color = if (isSelected) Orange450 else Black,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            textDecoration = if (isSelected) TextDecoration.Underline else null
        ),
        modifier = modifier
            .height(24.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .wrapContentSize(Alignment.CenterStart)
    )
}


@Composable
fun PriorityOptButton(text: String?, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val notSelected = text.isNullOrEmpty()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Gray30), RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(Gray80)
            .padding(12.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text ?: stringResource(R.string.choose_btn_label),
            style = if (notSelected) MaterialTheme.typography.titleSmall.copy(
                fontSize = 14.sp,
                color = FontBlack
            )
            else MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp, color = Orange500),
            textAlign = if (notSelected) TextAlign.Center else TextAlign.Start
        )

    }
}

@Preview
@Composable
fun PreviewPurposeButton() {
    ImdangAppNewTheme() {
        Column {
            PurposeButton(
                iconId = R.drawable.ic_real_resident,
                label = "Text",
                isSelected = false,
                onClick = {

                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            PurposeButton(
                iconId = R.drawable.ic_gap_investment,
                label = "Text",
                isSelected = true,
                onClick = {

                }
            )
        }
    }
}
@Preview
@Composable
fun PreviewRadioButtonItem() {
    ImdangAppNewTheme() {
        Column {
            RadioButtonItem(label = "Text", selected = false) { }
            RadioButtonItem(label = "Text", selected = true) { }
        }
    }
}
@Preview
@Composable
fun PreviewOPTText() {
    ImdangAppNewTheme() {
        Column {
            OptText("Text", false,{})
            OptText("Text", true,{})
        }
    }
}

@Preview
@Composable
fun PreviewPriorityOptButton() {
    ImdangAppNewTheme() {
        Column {
            PriorityOptButton("Text>text"){}
            Spacer(Modifier.height(10.dp))
            PriorityOptButton(null){}
        }
    }
}