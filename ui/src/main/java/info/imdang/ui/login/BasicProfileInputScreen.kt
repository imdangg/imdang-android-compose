package info.imdang.ui.login

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import info.imdang.imdang.core.component.theme.Gray700
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.ui.R
import info.imdang.core.component.R as ComponentR

@Composable
fun BasicProfileInputRoute(
    viewModel: BasicProfileInputViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    var nickName by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf<Gender?>(null) }

    BasicProfileInputScreen(
        onBackClick = onBackClick,
        nickName = nickName,
        onNicknameChange = { nickName = it },
        birthDay = birthDay,
        onBirthDayChange = { birthDay = it },
        gender = gender,
        onGenderChange = { gender = it },
        onClickedComplete = {}
    )
}

@Composable
internal fun BasicProfileInputScreen(
    onBackClick: () -> Unit,
    nickName: String,
    onNicknameChange: (String) -> Unit,
    birthDay: String,
    onBirthDayChange: (String) -> Unit,
    gender: Gender?,
    onGenderChange: (Gender) -> Unit,
    onClickedComplete: () -> Unit,
) {
    val nicknameIsError = nickName.isNotEmpty() && (nickName.length < 2 || nickName.length > 10)
    val nicknameIsSuccess = nickName.length in 2..10

    val birthDayRegex = Regex("^\\d{4}\\.\\d{2}\\.\\d{2}$")
    val birthDayIsError = birthDay.isNotEmpty() && !birthDayRegex.matches(birthDay)
    val birthDayIsSuccess = birthDayRegex.matches(birthDay)

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
                isError = nicknameIsError,
                errorMessage = "",
                isSuccess = nicknameIsSuccess
            )

            TextInput(
                modifier = Modifier
                    .fillMaxWidth(),
                inputType = TextInputType.INPUT,
                value = birthDay,
                onValueChanged = onBirthDayChange,
                label = stringResource(R.string.basic_profile_birth),
                labelDescription = "",
                placeHolder = "YYYY.MM.DD",
                maxLength = 10,
                isError = birthDayIsError,
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

@ImdangPreview
@Composable
private fun BasicProfileInputScreenPreview() {
    ImdangAppNewTheme {
        var nickName by remember { mutableStateOf("입력한 정보") }
        var birthDay by remember { mutableStateOf("1997.05.05") }
        var gender by remember { mutableStateOf<Gender?>(Gender.FEMALE) }

        BasicProfileInputScreen(
            onBackClick = { },
            nickName = nickName,
            onNicknameChange = { nickName = it },
            birthDay = birthDay,
            onBirthDayChange = { birthDay = it },
            gender = gender,
            onGenderChange = { gender = it },
            onClickedComplete = {}
        )
    }
}