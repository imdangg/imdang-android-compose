package info.imdang.ui.onboarding

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import info.imdang.core.presentation.onboarding.OnboardingStep
import info.imdang.core.presentation.onboarding.OnboardingViewModel
import info.imdang.core.presentation.onboarding.PreferenceCategory
import info.imdang.core.presentation.onboarding.PreferenceSubCategory
import info.imdang.core.presentation.onboarding.UserPurpose
import info.imdang.imdang.core.component.buttons.ButtonSize
import info.imdang.imdang.core.component.buttons.MainButton
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.Gray700
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.Orange300
import info.imdang.imdang.core.component.theme.Orange50
import info.imdang.imdang.core.component.theme.Orange500
import info.imdang.imdang.core.component.theme.Purple40
import info.imdang.imdang.core.component.theme.White
import info.imdang.imdang.core.component.theme.pretendardFont
import info.imdang.ui.R

@Composable
fun Onboarding(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val step = viewModel.step
    val purpose = viewModel.purpose

    LaunchedEffect(Unit) {
        purpose?.let {
            viewModel.loadData(it)
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(), color = White
    ) {
        when (step) {
            OnboardingStep.STEP0 -> Step0Screen(
                onPurposeSelected = { selectedPurpose ->
                    viewModel.updatePurpose(selectedPurpose)
                    viewModel.nextStep()
                }
            )

            OnboardingStep.STEP1, OnboardingStep.STEP2, OnboardingStep.STEP3, OnboardingStep.STEP4 -> Step1To4Screen(
                onNext = { viewModel.nextStep() }
            )

            OnboardingStep.OPT_STEP5 -> OptStep5Screen(
                commuteArea = viewModel.commuteArea.value,
                onFinish = {})

            OnboardingStep.FINISHED -> FinishedScreen()
        }
    }
}

@Composable
fun Step0Screen(onPurposeSelected: (UserPurpose) -> Unit) {

    var selectedPurpose by remember { mutableStateOf<UserPurpose?>(null) }
    Log.e("STEP0_Purpose","${selectedPurpose?.name}")
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            modifier = Modifier.padding(start = 20.dp, top = 80.dp),
            text = "어떤 분야에\n관심 있으신가요?",
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 85.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            PurposeButton(
                iconId = R.drawable.ic_real_resident,
                label = "실거주",
                isSelected = selectedPurpose == UserPurpose.REAL_RESIDENCE,
                onClick = {
                    selectedPurpose = if (selectedPurpose == UserPurpose.REAL_RESIDENCE) null
                    else UserPurpose.REAL_RESIDENCE
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            PurposeButton(
                iconId = R.drawable.ic_gap_investment,
                label = "갭투자",
                isSelected = selectedPurpose == UserPurpose.GAP_INVESTMENT,
                onClick = {
                    selectedPurpose = if (selectedPurpose == UserPurpose.GAP_INVESTMENT) null
                    else UserPurpose.GAP_INVESTMENT
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        MainButton(
            onClick = {
                val currentSelectedPurpose = selectedPurpose
                currentSelectedPurpose?.let{onPurposeSelected(it)}},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 24.dp),
            buttonSize = ButtonSize.L,
            text = stringResource(R.string.next_btn_label),
            enabled = true
        )
    }
}


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
            Text(text = label,style = MaterialTheme.typography.titleMedium)
        }
    }
}



@Composable
fun Step1To4Screen(onNext: () -> Unit) {

}



@Composable
fun OptStep5Screen(commuteArea: PreferenceCategory?, onFinish: () -> Unit) {

}


@Composable
fun FinishedScreen() {

}

@Preview
@Composable
fun PreviewStep0() {
    Surface(modifier = Modifier.fillMaxSize(), color = White) {
        ImdangAppNewTheme() {
            Step0Screen {

            }
        }
    }

    val dummyPreferenceCategory = PreferenceCategory.CommuteArea(
        purpose = UserPurpose.REAL_RESIDENCE,
        title = "더미 출퇴근 지역",
        subCategories = listOf(
            PreferenceSubCategory("강남구", listOf("역삼동", "삼성동", "청담동")),
            PreferenceSubCategory("광화문", listOf("종로1가", "종로2가", "종로3가")),
            PreferenceSubCategory("성수동", listOf("성수1가", "성수2가"))
        )
    )
    OptStep5Screen(dummyPreferenceCategory) { }

}