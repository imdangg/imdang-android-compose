package info.imdang.ui.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import info.imdang.core.presentation.onboarding.OnboardingStep
import info.imdang.core.presentation.onboarding.OnboardingViewModel
import info.imdang.core.presentation.onboarding.PreferenceCategory
import info.imdang.core.presentation.onboarding.PreferenceSubCategory
import info.imdang.core.presentation.onboarding.UserPurpose

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

@Composable
fun Step0Screen(onPurposeSelected: (UserPurpose) -> Unit) {

}

@Preview
@Composable
fun PreviewStep0() {
    Step0Screen { }
}

@Composable
fun Step1To4Screen(onNext: () -> Unit) {

}

@Preview
@Composable
fun PreviewStep1To4() {
    Step1To4Screen { }
}


@Composable
fun OptStep5Screen(commuteArea: PreferenceCategory?, onFinish: () -> Unit) {

}

@Preview
@Composable
fun PreviewOptStep5() {
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


@Preview
@Composable
fun FinishedScreen(){

}