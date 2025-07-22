package info.imdang.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import info.imdang.core.presentation.login.BasicProfileInputViewModel
import info.imdang.core.presentation.login.Gender
import info.imdang.imdang.core.component.buttons.ButtonSize
import info.imdang.imdang.core.component.buttons.InputButton
import info.imdang.imdang.core.component.buttons.MainButton
import info.imdang.imdang.core.component.textinput.TextInput
import info.imdang.imdang.core.component.textinput.TextInputType
import info.imdang.imdang.core.component.theme.Gray50
import info.imdang.imdang.core.component.theme.Gray700
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.ui.R
import info.imdang.core.component.R as ComponentR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicProfileInputRoute(
    viewModel: BasicProfileInputViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    var nickName by remember { mutableStateOf("") }
    var birthDayField by remember { mutableStateOf(TextFieldValue("")) }
    var gender by remember { mutableStateOf<Gender?>(null) }

    val sheetState = rememberModalBottomSheetState()
    var isSheetVisible by remember { mutableStateOf(false) }

    BasicProfileInputScreen(
        onBackClick = onBackClick,
        nickName = nickName,
        onNicknameChange = { nickName = it },
        birthDayField = birthDayField,
        onBirthDayChange = { birthDayField = it },
        gender = gender,
        onGenderChange = { gender = it },
        onClickedComplete = { isSheetVisible = true }
    )

    if (isSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isSheetVisible = false },
            sheetState = sheetState
        ) {
            ServiceAgreementSheetContent(
                onAgreeClick = { marketingAgreed ->
                    isSheetVisible = false
                },
                onDismiss = { isSheetVisible = false },
                onClickUrl = { url ->

                }
            )
        }
    }
}

@Composable
internal fun BasicProfileInputScreen(
    onBackClick: () -> Unit,
    nickName: String,
    onNicknameChange: (String) -> Unit,
    birthDayField: TextFieldValue,
    onBirthDayChange: (TextFieldValue) -> Unit,
    gender: Gender?,
    onGenderChange: (Gender) -> Unit,
    onClickedComplete: () -> Unit,
) {
    val nicknameIsSuccess = nickName.length in 2..10
    val birthDayIsSuccess = BirthDateValidator.validate(birthDayField.text)
    val isButtonEnabled = nicknameIsSuccess && birthDayIsSuccess && gender != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 21.dp)
        ) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(ComponentR.drawable.back),
                    contentDescription = "Back Button",
                )
            }

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.basic_profile_input_title),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    color = Gray900
                )
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(R.string.basic_profile_input_description),
                style = MaterialTheme.typography.labelSmall.copy(Gray700)
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 56.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            TextInput(
                modifier = Modifier
                    .fillMaxWidth(),
                inputType = TextInputType.SINGLE_LINE,
                value = nickName,
                onValueChanged = onNicknameChange,
                label = stringResource(R.string.basic_profile_nickname),
                labelDescription = stringResource(R.string.nickname_length_hint),
                placeHolder = "",
                maxLength = 10,
                isError = nickName.isNotEmpty() && !nicknameIsSuccess,
                errorMessage = "",
                isSuccess = nicknameIsSuccess
            )

            TextInput(
                modifier = Modifier
                    .fillMaxWidth(),
                inputType = TextInputType.INPUT,
                value = birthDayField,
                onValueChanged = {
                    val formatted = BirthDateValidator.format(it.text)
                    onBirthDayChange(
                        TextFieldValue(
                            text = formatted,
                            selection = TextRange(formatted.length)
                        )
                    )
                },
                label = stringResource(R.string.basic_profile_birth),
                labelDescription = "",
                placeHolder = "YYYY.MM.DD",
                maxLength = 10,
                isError = birthDayField.text.isNotEmpty() && !birthDayIsSuccess,
                errorMessage = "",
                isSuccess = birthDayIsSuccess
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.basic_profile_gender),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            color = Gray700
                        )
                    )
                    if (gender != null) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(info.imdang.core.component.R.drawable.circle_check_orange),
                            contentDescription = "State Icon",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    InputButton(
                        onClick = { onGenderChange(Gender.MALE) },
                        text = stringResource(R.string.basic_profile_gender_male),
                        state = gender == Gender.MALE,
                        modifier = Modifier
                            .height(52.dp)
                            .weight(1f)
                    )

                    InputButton(
                        onClick = { onGenderChange(Gender.FEMALE) },
                        text = stringResource(R.string.basic_profile_gender_female),
                        state = gender == Gender.FEMALE,
                        modifier = Modifier
                            .height(52.dp)
                            .weight(1f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        MainButton(
            onClick = onClickedComplete,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            buttonSize = ButtonSize.L,
            text = stringResource(R.string.btn_complete),
            enabled = isButtonEnabled
        )
    }
}

@Composable
fun ServiceAgreementSheetContent(
    onAgreeClick: (marketingAgreed: Boolean) -> Unit,
    onDismiss: () -> Unit,
    onClickUrl: (String) -> Unit,
) {
    val agreementStates = remember {
        mutableStateMapOf<AgreementItem, Boolean>().apply {
            AgreementItem.all.forEach { put(it, false) }
        }
    }

    val allAgreed = AgreementItem.all.all { agreementStates[it] == true }
    val requiredAgreed =
        AgreementItem.all.filter { it.required }.all { agreementStates[it] == true }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.basic_profile_input_title),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    color = Gray900
                )
            )

            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onDismiss() },
                painter = painterResource(ComponentR.drawable.cancle),
                contentDescription = "Back Button",
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Gray50, RoundedCornerShape(8.dp))
                    .clickable {
                        val newState = !allAgreed
                        AgreementItem.all.forEach {
                            agreementStates[it] = newState
                        }
                    }
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = ComponentR.drawable.circle_check),
                    contentDescription = "circle_check",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "전체 동의",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = Gray900
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AgreementItem.all.forEach { item ->
                    val isChecked = agreementStates[item] == true
                    val prefix = stringResource(
                        if (item.required) R.string.agreement_essential else R.string.agreement_selection
                    )
                    AgreementItemRow(
                        text = "$prefix ${stringResource(id = item.labelRes)}",
                        isChecked = isChecked,
                        onClick = {
                            agreementStates[item] = !isChecked
                        },
                        onClickTerms = {
                            onClickUrl(item.url)
                        }
                    )
                }
            }
        }

        MainButton(
            onClick = {
                val marketingAgreed = agreementStates[AgreementItem.MarketingConsent] == true
                onAgreeClick(marketingAgreed)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 40.dp),
            buttonSize = ButtonSize.L,
            text = "동의하고 계속하기",
            enabled = requiredAgreed
        )
    }
}

@Composable
private fun AgreementItemRow(
    text: String,
    isChecked: Boolean,
    onClick: () -> Unit,
    onClickTerms: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(24.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(
                id = if (isChecked) ComponentR.drawable.circle_check_fill else ComponentR.drawable.circle_check
            ),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(
                color = Gray900
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = ComponentR.drawable.right),
            contentDescription = null,
            modifier = Modifier
                .size(12.dp)
                .clickable { onClickTerms() },
        )
    }
}

@ImdangPreview
@Composable
private fun BasicProfileInputScreenPreview() {
    ImdangAppNewTheme {
        var nickName by remember { mutableStateOf("입력한 정보") }
        var birthDayField by remember { mutableStateOf(TextFieldValue("1997.05.05")) }
        var gender by remember { mutableStateOf<Gender?>(Gender.FEMALE) }

        BasicProfileInputScreen(
            onBackClick = { },
            nickName = nickName,
            onNicknameChange = { nickName = it },
            birthDayField = birthDayField,
            onBirthDayChange = { birthDayField = it },
            gender = gender,
            onGenderChange = { gender = it },
            onClickedComplete = {}
        )
    }
}

@ImdangPreview
@Composable
private fun ServiceAgreementSheetContentPreview() {
    ImdangAppNewTheme {
        ServiceAgreementSheetContent(
            onAgreeClick = {},
            onDismiss = {},
            onClickUrl = {}
        )
    }
}