package info.imdang.ui.main.write

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import info.imdang.imdang.core.component.theme.GrayScale400
import info.imdang.imdang.core.component.theme.GrayScale900
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.ui.R

@Composable
internal fun ContentDescriptionSection(
    modifier: Modifier = Modifier,
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit,
    titleMaxLen: Int = 20,
    contentMaxLen: Int = 1000,
) {
    val focusManager = LocalFocusManager.current
    val contentFocus = remember { FocusRequester() }

    val tfColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent
    )

    Column(modifier = modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = title,
            onValueChange = { onTitleChange(it.take(titleMaxLen)) },
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_title_placeholder),
                    style = MaterialTheme.typography.titleSmall.copy(GrayScale400)
                )
            },
            textStyle = MaterialTheme.typography.titleSmall.copy(GrayScale900),
            colors = tfColors,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { contentFocus.requestFocus() }
            ),
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 2.dp, end = 20.dp)
        )

        TextField(
            value = content,
            onValueChange = { onContentChange(it.take(contentMaxLen)) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(contentFocus)
                .heightIn(min = 126.dp)
                .padding(start = 4.dp, top = 2.dp, end = 4.dp),
            placeholder = {
                Text(
                    text = stringResource(R.string.share_visit_review_placeholder),
                    style = MaterialTheme.typography.bodyMedium.copy(GrayScale400)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(GrayScale900),
            colors = tfColors,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Default
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )
    }
}


@ImdangPreview
@Composable
private fun ContentDescriptionSectionPreview() {
    ImdangAppNewTheme {
        var title by rememberSaveable { mutableStateOf("") }
        var content by rememberSaveable { mutableStateOf("") }

        ContentDescriptionSection(
            modifier = Modifier
                .fillMaxWidth(),
            title = title,
            onTitleChange = { title = it },
            content = content,
            onContentChange = { content = it },
        )
    }
}
