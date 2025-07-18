package info.imdang.ui.onboarding

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import info.imdang.core.presentation.onboarding.OnboardingStep
import info.imdang.core.presentation.onboarding.OnboardingText
import info.imdang.core.presentation.onboarding.OnboardingViewModel
import info.imdang.core.presentation.onboarding.PreferenceCategory
import info.imdang.core.presentation.onboarding.PreferenceSubCategory
import info.imdang.core.presentation.onboarding.UserPurpose
import info.imdang.imdang.core.component.buttons.ButtonSize
import info.imdang.imdang.core.component.buttons.MainButton
import info.imdang.imdang.core.component.taps.ScrollableTabRow
import info.imdang.imdang.core.component.theme.FontBlack
import info.imdang.imdang.core.component.theme.Gray150
import info.imdang.imdang.core.component.theme.Gray30
import info.imdang.imdang.core.component.theme.Gray550
import info.imdang.imdang.core.component.theme.Gray80
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.White
import info.imdang.ui.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Onboarding(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val currentStep = viewModel.step
    val currentPurpose = viewModel.purpose

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(), color = White
    ) {
        when (currentStep) {
            OnboardingStep.STEP0 -> Step0Screen(
                onPurposeSelected = { selectedPurpose ->
                    viewModel.updatePurpose(selectedPurpose)
                    viewModel.nextStep()
                }
            )

            OnboardingStep.STEP1, OnboardingStep.STEP2, OnboardingStep.STEP3, OnboardingStep.STEP4 -> {
                Step1To4Screen(
                    currentStep, currentPurpose!!, viewModel.onboardingSelections,
                    onOptionSelected = { index ->
                        viewModel.updateSelectionForStep(
                            currentStep,
                            index
                        )
                    },
                    onNext = { viewModel.nextStep() },
                    onBackClick = {
                        viewModel.clearSelectionForStep(currentStep)
                        viewModel.backStep()
                    }
                )
            }

            OnboardingStep.OPT_STEP5_1, OnboardingStep.OPT_STEP5_2 -> OptStep5Screen(
                currentStep, currentPurpose!!,
                commuteArea = viewModel.commuteArea.value,
                onFinish = {})

            OnboardingStep.FINISHED -> FinishedScreen()
        }
    }
}

@Composable
fun Step0Screen(onPurposeSelected: (UserPurpose) -> Unit) {

    var selectedPurpose by remember { mutableStateOf<UserPurpose?>(null) }
    Log.e("STEP0_Purpose", "${selectedPurpose?.name}")
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            modifier = Modifier.padding(start = 20.dp, top = 80.dp),
            text = "어떤 분야에\n관심 있으신가요?",
            style = MaterialTheme.typography.titleLarge.copy(FontBlack)
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
                currentSelectedPurpose?.let { onPurposeSelected(it) }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            buttonSize = ButtonSize.L,
            text = stringResource(R.string.next_btn_label),
            enabled = true
        )
    }
}


@Composable
fun Step1To4Screen(
    currentStep: OnboardingStep,
    currentPurpose: UserPurpose,
    onboardingSelections: Map<OnboardingStep, Int>,
    onOptionSelected: (Int) -> Unit,
    onNext: () -> Unit,
    onBackClick: () -> Unit
) {
    val currentOnboardingText = OnboardingText.entries.find {
        it.step == currentStep && it.purpose == currentPurpose
    }
    val selectedIndex = onboardingSelections[currentStep] ?: -1
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        currentOnboardingText?.let {
            Icon(
                painter = painterResource(info.imdang.core.component.R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() }
            )
            Text(
                modifier = Modifier.padding(top = 36.dp),
                text = it.title,
                style = MaterialTheme.typography.titleLarge.copy(FontBlack)
            )
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 40.dp),
                text = it.subtitle,
                style = MaterialTheme.typography.labelMedium.copy(Gray550)
            )
            it.options.forEachIndexed { index, label ->
                RadioButtonItem(
                    label = label,
                    selected = selectedIndex == index
                ) {
                    onOptionSelected(index)
                    coroutineScope.launch {
                        delay(150)
                        onNext()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptStep5Screen(
    currentStep: OnboardingStep,
    currentPurpose: UserPurpose,
    commuteArea: PreferenceCategory?,
    onFinish: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val coroutineScope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val currentOnboardingText = OnboardingText.entries.find {
        it.step == currentStep && it.purpose == currentPurpose
    }
    val tab by remember(currentOnboardingText) {
        derivedStateOf {
            currentOnboardingText?.options.orEmpty()
        }
    }
    var selectedPriorityIndex by remember { mutableStateOf<Int?>(null) }
    Log.d("5Screen", "${currentOnboardingText?.name} null 인가 ?")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        currentOnboardingText?.let {

            Icon(
                painter = painterResource(info.imdang.core.component.R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { }
            )
            Text(
                modifier = Modifier.padding(top = 36.dp),
                text = it.title,
                style = MaterialTheme.typography.titleLarge.copy(FontBlack)
            )
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 40.dp),
                text = it.subtitle,
                style = MaterialTheme.typography.labelMedium.copy(Gray550)
            )
            for (i in 1..3) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "${i}위",
                    style = MaterialTheme.typography.titleMedium.copy(FontBlack)
                )
                PriorityOptButton() {
                    selectedPriorityIndex = i
                    showBottomSheet = true
                    coroutineScope.launch {
                        sheetState.show()
                    }
                }
                Spacer(Modifier.height(24.dp))
            }

        }

    }

    if (showBottomSheet && commuteArea != null && selectedPriorityIndex != null) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch { sheetState.hide() }
                showBottomSheet = false
                selectedPriorityIndex = null // Reset
            },
            sheetState = sheetState,
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(width = 52.dp, height = 6.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Gray150)
                )
            },
            content = {
                BottomSheetContent(
                    modifier = Modifier.fillMaxHeight(0.7f),
                    commuteArea = commuteArea,
                    tab = tab,
                    priorityIndex = selectedPriorityIndex!!,
                    onFinish = {
                        coroutineScope.launch { sheetState.hide() }
                        showBottomSheet = false
                        selectedPriorityIndex = null // Reset
                        onFinish()
                    })
            },
            containerColor = White,
        )
    } else {
        Log.d(
            "OptStep5Screen",
            "ModalBottomSheet 조건 미충족. showBottomSheet=$showBottomSheet, commuteArea=$commuteArea"
        )
    }
}

@Composable
fun PriorityOptButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Gray30), RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(Gray80)
            .padding(vertical = 12.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "선택",
            style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp, color = FontBlack)
        )

    }
}

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier,
    priorityIndex: Int,
    tab: List<String>,
    commuteArea: PreferenceCategory,
    onFinish: () -> Unit
) {
    val allCategories = remember {
        PreferenceCategory.statics + commuteArea
    }
    val mappedTabCategories: List<PreferenceCategory?> = remember(tab, allCategories) {
        tab.map { tabTitle ->
            allCategories.find { it.title == tabTitle }
        }
    }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tab.size })
    val coroutineScope = rememberCoroutineScope()

    var selectedOption by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "${priorityIndex}순위",
            style = MaterialTheme.typography.titleLarge.copy(color = FontBlack)
        )

        ScrollableTabRow(
            tabs = tab,
            selectedIndex = pagerState.currentPage,
            onTabSelected = { index ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            val currentCategory: PreferenceCategory? = mappedTabCategories.getOrNull(page)

            if (currentCategory != null) {
                when (currentCategory) {
                    is PreferenceCategory.CommuteArea -> { // CommuteArea 타입인 경우 (가장 첫 탭)
                        var selectedSubCategoryIndex by remember { mutableIntStateOf(0) }
                        Row(modifier = Modifier.fillMaxSize()) {
                            // 서울 구
                            Column(
                                modifier = Modifier
                                    .verticalScroll(rememberScrollState())
                                    .weight(0.5f)
                                    .padding(end = 8.dp)
                            ) {
                                currentCategory.subCategories.forEachIndexed { index, subCategory ->
                                    OptText(
                                        text = subCategory.name,
                                        isSelected = selectedSubCategoryIndex == index,
                                        onClick = {
                                            selectedSubCategoryIndex = index
                                            selectedOption = null
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(Modifier.height(24.dp))
                                }
                            }

                            // 우측 Column: 선택된 subCategory의 details (동 이름) 목록
                            Column(
                                modifier = Modifier
                                    .verticalScroll(rememberScrollState())
                                    .weight(0.5f)
                            ) {
                                val selectedSubCategory =
                                    currentCategory.subCategories.getOrNull(selectedSubCategoryIndex)

                                selectedSubCategory?.options?.forEachIndexed { index,detail ->
                                    OptText(
                                        text = detail,
                                        isSelected = selectedOption == detail,
                                        onClick = {
                                            selectedOption = detail
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(Modifier.height(24.dp))
                                }

                            }
                        }
                    }
                    // CommuteArea가 아닌 다른 PreferenceCategory 타입들 (Traffic, School, etc.)
                    else -> {
                        Column(modifier = Modifier.fillMaxSize()) {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(2),
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                                    verticalArrangement = Arrangement.spacedBy(24.dp),
                                ) {
                                    items(currentCategory.options) { option ->
                                        OptText(
                                            text = option,
                                            isSelected = selectedOption == option,
                                            onClick = { selectedOption = option },
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                            }
                        }
                    }
                }
            } else {
                // 매칭되는 카테고리가 없는 경우 (tab 리스트 데이터 누락)
                Text("데이터를 찾을 수 없습니다: ${tab.getOrNull(page)}", modifier = Modifier.fillMaxSize())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onFinish,
            enabled = selectedOption != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("선택")
        }
    }
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
}



@Preview
@Composable
fun PreviewStep1To4() {
    Surface(modifier = Modifier.fillMaxSize(), color = White) {

        ImdangAppNewTheme() {
            Step1To4Screen(
                OnboardingStep.STEP1, UserPurpose.REAL_RESIDENCE,
                mapOf(OnboardingStep.OPT_STEP5_1 to 1), {}, {}) { }
        }
    }
}

@Preview
@Composable
fun PreviewOPTStep5() {
    val dummyPreferenceCategory = PreferenceCategory.CommuteArea(
        title = "더미 출퇴근 지역",
        subCategories = listOf(
            PreferenceSubCategory("강남구", listOf("역삼동", "삼성동", "청담동")),
            PreferenceSubCategory("광화문", listOf("종로1가", "종로2가", "종로3가")),
            PreferenceSubCategory("성수동", listOf("성수1가", "성수2가"))
        )
    )
    Surface(modifier = Modifier.fillMaxSize(), color = White) {
        ImdangAppNewTheme() {
            OptStep5Screen(
                OnboardingStep.OPT_STEP5_1,
                UserPurpose.REAL_RESIDENCE,
                dummyPreferenceCategory
            ) { }
        }
    }
}



