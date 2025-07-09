package info.imdang.core.presentation.onboarding

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

enum class OnboardingStep {
    STEP0, STEP1, STEP2, STEP3, STEP4, OPT_STEP5,FINISHED
}

enum class UserPurpose {
    REAL_RESIDENCE, GAP_INVESTMENT
}

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    var step by mutableStateOf(OnboardingStep.STEP0)
        private set

    var purpose by mutableStateOf<UserPurpose?>(null)
        private set

    val commuteArea = mutableStateOf<PreferenceCategory?>(null)


    fun updatePurpose(selectedPurpose: UserPurpose) {
        purpose = selectedPurpose
    }

    fun nextStep() {
        step = when (step) {
            OnboardingStep.STEP0 -> OnboardingStep.STEP1
            OnboardingStep.STEP1 -> OnboardingStep.STEP2
            OnboardingStep.STEP2 -> OnboardingStep.STEP3
            OnboardingStep.STEP3 -> OnboardingStep.STEP4
            OnboardingStep.STEP4 -> OnboardingStep.OPT_STEP5
            OnboardingStep.OPT_STEP5 -> OnboardingStep.FINISHED
            OnboardingStep.FINISHED -> OnboardingStep.FINISHED
        }
    }

    fun loadData(purpose: UserPurpose) {
        commuteArea.value = PreferenceCategory.loadFromJson(context, purpose)
    }

}