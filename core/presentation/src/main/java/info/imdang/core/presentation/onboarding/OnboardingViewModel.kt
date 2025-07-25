package info.imdang.core.presentation.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.imdang.core.presentation.model.RankedPriority
import info.imdang.imdang.core.domain.usecase.GetSavedSeoulAreaDataUseCase
import info.imdang.imdang.core.domain.usecase.LoadSeoulAreaFromJsonUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class OnboardingStep {
    STEP0, STEP1, STEP2, STEP3, STEP4, OPT_STEP5, OPT_STEP6, FINISHED
}

enum class UserPurpose {
    REAL_RESIDENCE, GAP_INVESTMENT
}

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val loadSeoulAreaFromJsonUseCase: LoadSeoulAreaFromJsonUseCase,
    private val getSavedSeoulAreaDataUseCase: GetSavedSeoulAreaDataUseCase

) : ViewModel() {
    var step by mutableStateOf(OnboardingStep.STEP0)
        private set
    var purpose by mutableStateOf<UserPurpose?>(null)
        private set

    val commuteArea = mutableStateOf<PreferenceCategory?>(null)

    //todo request model 생성되면 Int 에서 data class 로 변경 예정
    private val _onboardingSelections = mutableStateOf<Map<OnboardingStep, Int>>(emptyMap())
    val onboardingSelections: Map<OnboardingStep, Int>
        get() = _onboardingSelections.value

    private val _rankedPriorities = mutableStateOf<List<RankedPriority>>(emptyList())
    val rankedPriorities: List<RankedPriority>
        get() = _rankedPriorities.value

    private val _preferredAreas = mutableStateOf<List<String>>(emptyList())
    val preferredAreas: List<String>
        get() = _preferredAreas.value

    init {
        viewModelScope.launch {
            loadSeoulAreaData()
            setCommuteData()
        }
    }

    fun updatePurpose(selectedPurpose: UserPurpose) {
        purpose = selectedPurpose
    }

    fun updateSelectionForStep(targetStep: OnboardingStep, selectedIndex: Int) {
        _onboardingSelections.value = _onboardingSelections.value.toMutableMap().apply {
            this[targetStep] = selectedIndex
        }
    }

    fun getSelectionForStep(targetStep: OnboardingStep): Int? {
        return _onboardingSelections.value[targetStep]
    }

    fun clearSelectionForStep(targetStep: OnboardingStep) {
        _onboardingSelections.value = _onboardingSelections.value.toMutableMap().apply {
            remove(targetStep)
        }
    }

    fun updateRankedPriorities(rankedList: List<RankedPriority>) {
        _rankedPriorities.value = rankedList.sortedBy { it.rank }
    }

    fun clearRankedPriorities() {
        _rankedPriorities.value = emptyList()
    }

    fun updatePreferredAreas(areas: List<String>) {
        _preferredAreas.value = areas
    }

    fun clearPreferredAreas() {
        _preferredAreas.value = emptyList()
    }

    fun nextStep() {
        step = when (step) {
            OnboardingStep.STEP0 -> {
                if (purpose != null) {
                    OnboardingStep.STEP1
                } else {
                    step
                }
            }

            OnboardingStep.STEP1 -> OnboardingStep.STEP2
            OnboardingStep.STEP2 -> OnboardingStep.STEP3
            OnboardingStep.STEP3 -> OnboardingStep.STEP4
            OnboardingStep.STEP4 -> OnboardingStep.OPT_STEP5
            OnboardingStep.OPT_STEP5 -> OnboardingStep.OPT_STEP6
            OnboardingStep.OPT_STEP6 -> OnboardingStep.FINISHED
            OnboardingStep.FINISHED -> OnboardingStep.FINISHED
        }
    }

    fun backStep() {
        step = when (step) {
            OnboardingStep.STEP0 -> OnboardingStep.STEP0
            OnboardingStep.STEP1 -> OnboardingStep.STEP0
            OnboardingStep.STEP2 -> OnboardingStep.STEP1
            OnboardingStep.STEP3 -> OnboardingStep.STEP2
            OnboardingStep.STEP4 -> OnboardingStep.STEP3
            OnboardingStep.OPT_STEP5 -> OnboardingStep.STEP4
            OnboardingStep.OPT_STEP6 -> OnboardingStep.OPT_STEP5
            OnboardingStep.FINISHED -> OnboardingStep.FINISHED
        }
    }

    private fun loadSeoulAreaData() {
        loadSeoulAreaFromJsonUseCase.invoke()
    }

    private suspend fun setCommuteData() {
        val seoulDistricts = getSavedSeoulAreaDataUseCase.invoke()
        seoulDistricts.collect {list ->
            val subCategories = list.map {
                PreferenceSubCategory(it.district, it.dong)
            }
            commuteArea.value = PreferenceCategory.CommuteArea(
                title = COMMUTE_AREA,
                subCategories = subCategories
            )
        }
    }
}