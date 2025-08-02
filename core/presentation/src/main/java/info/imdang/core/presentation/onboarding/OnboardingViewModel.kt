package info.imdang.core.presentation.onboarding

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.imdang.core.presentation.model.OnboardingRequestModel
import info.imdang.core.presentation.model.RankedPriority
import info.imdang.core.presentation.model.toDomain
import info.imdang.core.presentation.onboarding.enums.OnboardingStep
import info.imdang.core.presentation.onboarding.enums.PreferenceCategory
import info.imdang.core.presentation.onboarding.enums.PreferenceCategoryType
import info.imdang.core.presentation.onboarding.enums.PreferenceSubCategory
import info.imdang.core.presentation.onboarding.enums.UserPurpose
import info.imdang.imdang.core.domain.usecase.GetSavedSeoulAreaDataUseCase
import info.imdang.imdang.core.domain.usecase.LoadSeoulAreaFromJsonUseCase
import info.imdang.imdang.core.domain.usecase.PostOnboardingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val loadSeoulAreaFromJsonUseCase: LoadSeoulAreaFromJsonUseCase,
    private val getSavedSeoulAreaDataUseCase: GetSavedSeoulAreaDataUseCase,
    private val postOnboardingUseCase: PostOnboardingUseCase

) : ViewModel() {
    private val tag = OnboardingViewModel::class.simpleName

    var step by mutableStateOf(OnboardingStep.STEP0)
        private set
    var purpose by mutableStateOf<UserPurpose?>(null)
        private set

    val commuteArea: MutableStateFlow<PreferenceCategory.CommuteArea?> = MutableStateFlow(null)

    private val _onboardingSelections =
        mutableStateOf<Map<OnboardingStep, Pair<Int, String>>>(emptyMap())
    val onboardingSelections: Map<OnboardingStep, Pair<Int, String>>
        get() = _onboardingSelections.value

    private val _rankedPriorities = mutableStateOf<List<RankedPriority>>(emptyList())
    val rankedPriorities: List<RankedPriority>
        get() = _rankedPriorities.value

    private val _preferredAreas = mutableStateOf<List<String>>(emptyList())
    private val preferredAreas: List<String>
        get() = _preferredAreas.value

    init {
        viewModelScope.launch {
            loadSeoulAreaData()
            setCommuteData()
        }
    }

    fun postOnboardingData(
        onSuccess: () -> Unit
    ) {
        val request = toOnboardingRequestModel()
        //데이터 수집 결과 확인용 로그 - 추후 제거 필요
        Log.d("ONBOARDING_RESULT", request.toString())
        viewModelScope.launch {
            postOnboardingUseCase(request.toDomain()).catch { e ->
                Log.e(tag, "postOnboarding 실패 : ${e.message}")
            }.collect {
                onSuccess()
            }
        }

    }

    fun updatePurpose(selectedPurpose: UserPurpose) {
        purpose = selectedPurpose
    }

    fun updateSelectionForStep(
        targetStep: OnboardingStep,
        selectedIndex: Int,
        selectedValue: String
    ) {
        _onboardingSelections.value = _onboardingSelections.value.toMutableMap().apply {
            this[targetStep] = selectedIndex to selectedValue
        }
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
        seoulDistricts.collect { list ->
            val subCategories = list.map {
                PreferenceSubCategory(it.district, it.dong)
            }
            commuteArea.value = PreferenceCategory.CommuteArea(
                type = PreferenceCategoryType.COMMUTE_AREA,
                subCategories = subCategories
            )
        }
    }


    private val stepKeyMappings: List<Triple<OnboardingStep, UserPurpose?, String>> = listOf(
        Triple(OnboardingStep.STEP1, null, "budget"),
        Triple(OnboardingStep.STEP2, null, "monthIncome"),
        Triple(OnboardingStep.STEP3, UserPurpose.REAL_RESIDENCE, "livingPerson"),
        Triple(OnboardingStep.STEP3, UserPurpose.GAP_INVESTMENT, "hopeGap"),
        Triple(OnboardingStep.STEP4, UserPurpose.REAL_RESIDENCE, "childrenPlan"),
        Triple(OnboardingStep.STEP4, UserPurpose.GAP_INVESTMENT, "investmentPeriod")
    )


    private fun toOnboardingRequestModel(): OnboardingRequestModel {
        // STEP1TO4 옵션
        val valueMap = mutableMapOf<String, String?>()
        stepKeyMappings
            .filter { (_, p, _) -> p == null || p == purpose }
            .forEach { (step, _, key) ->
                val selectedValue =
                    onboardingSelections.values.map { it.second }.getOrNull(step.ordinal)
                valueMap[key] = selectedValue

            }

        // 우선순위 (STEP5)
        val priorityStringsByRank = rankedPriorities.associateBy { it.rank }
        val firstPriorityStr = priorityStringsByRank[1]?.let { "${it.category},${it.priority}" }
        val secondPriorityStr = priorityStringsByRank[2]?.let { "${it.category},${it.priority}" }
        val thirdPriorityStr = priorityStringsByRank[3]?.let { "${it.category},${it.priority}" }
        //관심동네 3개(STEP6)
        val interestDistrictStr =
            preferredAreas.joinToString(separator = ",").takeIf { it.isNotBlank() }


        fun getPriorityForCategory(category: String): String? {
            return priorityStringsByRank.values
                .filter { it.category == category }.minByOrNull { it.rank }
                ?.priority
        }

        return OnboardingRequestModel(
            purpose = purpose!!.displayName,
            budget = valueMap["budget"]!!,
            monthIncome = valueMap["monthIncome"]!!,
            livingPerson = valueMap["livingPerson"],
            hopeGap = valueMap["hopeGap"],
            childrenPlan = valueMap["childrenPlan"],
            investmentPlan = valueMap["investmentPeriod"],
            traffic = getPriorityForCategory("교통"),
            schoolDistrict = getPriorityForCategory("학군"),
            apartmentSquare = getPriorityForCategory("아파트 평수"),
            household = getPriorityForCategory("세대수"),
            houseType = getPriorityForCategory("유형"),
            commutingArea = getPriorityForCategory("출퇴근 지역"),
            infra = getPriorityForCategory("인프라"),
            environment = getPriorityForCategory("환경"),
            firstPriority = firstPriorityStr,
            secondPriority = secondPriorityStr,
            thirdPriority = thirdPriorityStr,
            interestDistrict = interestDistrictStr
        )
    }


}

