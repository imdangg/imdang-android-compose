package info.imdang.ui.onboarding

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import info.imdang.core.presentation.model.RankedPriority
import info.imdang.core.presentation.onboarding.OnboardingStep
import info.imdang.core.presentation.onboarding.OnboardingText
import info.imdang.core.presentation.onboarding.OnboardingViewModel
import info.imdang.core.presentation.onboarding.PreferenceCategory
import info.imdang.core.presentation.onboarding.PreferenceSubCategory
import info.imdang.core.presentation.onboarding.UserPurpose
import info.imdang.imdang.core.component.buttons.ButtonSize
import info.imdang.imdang.core.component.buttons.MainButton
import info.imdang.imdang.core.component.taps.ScrollableTabRow
import info.imdang.imdang.core.component.theme.Black
import info.imdang.imdang.core.component.theme.FontBlack
import info.imdang.imdang.core.component.theme.Gray100
import info.imdang.imdang.core.component.theme.Gray150
import info.imdang.imdang.core.component.theme.Gray550
import info.imdang.imdang.core.component.theme.Gray700
import info.imdang.imdang.core.component.theme.Gray900
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.White
import info.imdang.ui.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Composable
fun OnboardingRoute(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onOnboardingFinished : () -> Unit
) {
    val currentStep = viewModel.step
    val currentPurpose = viewModel.purpose
    val currentOnboardingText by remember(currentStep, currentPurpose) {
        mutableStateOf(
            OnboardingText.entries.find {
                it.step == currentStep && it.purpose == currentPurpose
            }
        )
    }
    if(currentStep == OnboardingStep.FINISHED){
        LaunchedEffect(Unit){
            delay(1500) //1.5초 대기
            onOnboardingFinished()
        }
    }
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
                    currentStep, currentOnboardingText, viewModel.onboardingSelections,
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

            OnboardingStep.OPT_STEP5 -> OptStep5Screen(
                currentOnboardingText,
                commuteArea = viewModel.commuteArea.value,
                initialRankedList = viewModel.rankedPriorities,
                onSkip = { viewModel.nextStep() },
                onBackClick = {
                    viewModel.clearRankedPriorities()
                    viewModel.backStep()
                },
                onNext = { rankedPriorities ->
                    viewModel.updateRankedPriorities(rankedPriorities)
                    viewModel.nextStep()
                }
            )

            OnboardingStep.OPT_STEP6 -> OptStep6Screen(
                currentOnboardingText,
                commuteArea = viewModel.commuteArea.value,
                onSkip = { viewModel.nextStep() },
                onBackClick = {
                    viewModel.clearPreferredAreas()
                    viewModel.backStep()
                },
                onFinish = { areas -> // todo api 개발 완료시 전송 메소드 호출
                    viewModel.updatePreferredAreas(areas)
                    viewModel.nextStep()
                }
            )

            OnboardingStep.FINISHED -> {
                FinishedScreen()

            }
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
                label = stringResource(R.string.purpose_real_resident),
                isSelected = selectedPurpose == UserPurpose.REAL_RESIDENCE,
                onClick = {
                    selectedPurpose = if (selectedPurpose == UserPurpose.REAL_RESIDENCE) null
                    else UserPurpose.REAL_RESIDENCE
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            PurposeButton(
                iconId = R.drawable.ic_gap_investment,
                label = stringResource(R.string.purpose_gap_investment),
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
    currentOnboardingText: OnboardingText?,
    onboardingSelections: Map<OnboardingStep, Int>,
    onOptionSelected: (Int) -> Unit,
    onNext: () -> Unit,
    onBackClick: () -> Unit
) {
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
    currentOnboardingText: OnboardingText?,
    commuteArea: PreferenceCategory?,
    initialRankedList: List<RankedPriority>?,
    onNext: (List<RankedPriority>) -> Unit,
    onSkip: () -> Unit,
    onBackClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val tab by remember(currentOnboardingText) {
        derivedStateOf {
            currentOnboardingText?.options.orEmpty()
        }
    }
    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Hidden,
        skipHiddenState = false
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    var selectedPriorityIndex by remember { mutableStateOf<Int?>(null) }

    var selectedPriorityList by remember {
        mutableStateOf(initialRankedList ?: emptyList())
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 537.dp,
        sheetContainerColor = White,
        sheetDragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .size(width = 52.dp, height = 6.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(Gray150)
            )
        },

        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f),
            ) {
                if (showBottomSheet && commuteArea != null && selectedPriorityIndex != null) {
                    BottomSheetContent(
                        commuteArea = commuteArea,
                        tab = tab,
                        priorityIndex = selectedPriorityIndex!!,
                        onFinish = { rank, category, priority ->
                            coroutineScope.launch { sheetState.hide() }
                            showBottomSheet = false
                            selectedPriorityIndex = null // Reset
                            selectedPriorityList =
                                selectedPriorityList
                                    .filterNot { it.rank == rank || it.category == category } //todo category 일치시 snackBar 호출로 수정
                                    .plus(
                                        RankedPriority(rank, category, priority)
                                    )
                                    .sortedBy { it.rank }
                        })
                }
            }
        },
        containerColor = White,
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp)
                ) {
                    currentOnboardingText?.let { onboardingText ->
                        Icon(
                            painter = painterResource(info.imdang.core.component.R.drawable.back),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    onBackClick()
                                }
                        )
                        Text(
                            modifier = Modifier.padding(top = 36.dp),
                            text = onboardingText.title,
                            style = MaterialTheme.typography.titleLarge.copy(FontBlack)
                        )
                        Text(
                            modifier = Modifier.padding(top = 8.dp, bottom = 40.dp),
                            text = onboardingText.subtitle,
                            style = MaterialTheme.typography.labelMedium.copy(Gray550)
                        )

                        for (i in 1..3) {
                            val selectedForRank = selectedPriorityList.find { it.rank == i }
                            val selectedText =
                                selectedForRank?.let { "${it.category}>${it.priority}" }

                            Text(
                                modifier = Modifier.padding(bottom = 8.dp),
                                text = "${i}위",
                                style = MaterialTheme.typography.titleMedium.copy(FontBlack)
                            )
                            PriorityOptButton(
                                text = selectedText
                            ) {
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
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 4.dp)
                        .padding(20.dp),
                ) {
                    MainButton(
                        onClick = {
                            onNext(selectedPriorityList)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        buttonSize = ButtonSize.L,
                        text = stringResource(R.string.next_btn_label),
                        enabled = selectedPriorityList.size == 3,
                    )
                    Text(
                        modifier = Modifier
                            .clickable(
                                onClick = onSkip,
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                            .fillMaxWidth()
                            .padding(9.dp),
                        text = stringResource(R.string.skip_btn_label),
                        style = MaterialTheme.typography.titleSmall.copy(color = Gray700),
                        textAlign = TextAlign.Center
                    )
                }
                //bottomSheet open 시 scrim 영역(bottomSheet 바깥 영역) 색 입히기
                if (sheetState.currentValue != SheetValue.Hidden) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(Black.copy(alpha = 0.3f))
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                coroutineScope.launch { sheetState.hide() }
                            }
                    )
                }
            }

        }
    )
}


@Composable
fun OptStep6Screen(
    currentOnboardingText: OnboardingText?,
    commuteArea: PreferenceCategory?,
    onFinish: (List<String>) -> Unit,
    onSkip: () -> Unit,
    onBackClick: () -> Unit
) {
    val seoul = commuteArea?.subCategories?.map { it.name }.orEmpty()
    val selectedAreasState = remember { mutableStateOf<List<String>>(emptyList()) }
    val selectedAreas = selectedAreasState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(Modifier.padding(bottom = 130.dp)) {
            currentOnboardingText?.let { onboardingText ->
                Icon(
                    painter = painterResource(info.imdang.core.component.R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onBackClick() }
                )
                Text(
                    modifier = Modifier.padding(top = 36.dp),
                    text = onboardingText.title,
                    style = MaterialTheme.typography.titleLarge.copy(FontBlack)
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                    text = onboardingText.subtitle,
                    style = MaterialTheme.typography.labelMedium.copy(Gray550)
                )

                Text(
                    "서울",
                    modifier = Modifier
                        .height(43.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.labelMedium.copy(color = Gray900)
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = Gray100
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    items(seoul) { name ->
                        val isSelected = name in selectedAreas
                        OptText(
                            text = name,
                            isSelected = isSelected,
                            onClick = {
                                selectedAreasState.value = when {
                                    isSelected -> selectedAreas - name
                                    selectedAreas.size < 3 -> selectedAreas + name
                                    else -> selectedAreas // 3개 넘으면 무시
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 4.dp),
        ) {
            MainButton(
                onClick = {
                    onFinish(selectedAreas)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                buttonSize = ButtonSize.L,
                text = stringResource(R.string.next_btn_label),
                enabled = selectedAreas.size == 3
            )
            Text(
                modifier = Modifier
                    .clickable(
                        onClick = onSkip,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .fillMaxWidth()
                    .padding(9.dp),
                text = stringResource(R.string.skip_btn_label),
                style = MaterialTheme.typography.titleSmall.copy(color = Gray700),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun FinishedScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_onboarding))
    Box(Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 195.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(info.imdang.core.component.R.drawable.circle_check_orange),
                contentDescription = null,
                modifier = Modifier
                    .size(59.4.dp),
                tint = Color.Unspecified,
            )
            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "내 취향설정 완료!",
                style = MaterialTheme.typography.titleLarge.copy(color = Gray900)
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "아파트임당이 취향에 맞는 정보를\n" +
                        "큐레이션 해드릴게요!",
                style = MaterialTheme.typography.labelSmall.copy(color = Gray700),
                textAlign = TextAlign.Center
            )
        }
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(top = 60.dp),
            composition = composition,
            iterations = Int.MAX_VALUE
        )
    }
}


@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier,
    priorityIndex: Int,
    tab: List<String>,
    commuteArea: PreferenceCategory,
    onFinish: (Int, String, String) -> Unit
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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                )
                .padding(bottom = 76.dp) // bottom 버튼 공간
        ) {
            Text(
                text = "${priorityIndex}순위",
                style = MaterialTheme.typography.titleMedium.copy(color = FontBlack)
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
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                val currentCategory: PreferenceCategory? = mappedTabCategories.getOrNull(page)

                if (currentCategory != null) {
                    when (currentCategory) {
                        is PreferenceCategory.CommuteArea -> { // CommuteArea 타입인 경우
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

                                // 우측 Column: 선택된 subCategory 의 details (동 이름) 목록
                                Column(
                                    modifier = Modifier
                                        .verticalScroll(rememberScrollState())
                                        .weight(0.5f)
                                ) {
                                    val selectedSubCategory =
                                        currentCategory.subCategories.getOrNull(
                                            selectedSubCategoryIndex
                                        )

                                    selectedSubCategory?.options?.forEachIndexed { index, detail ->
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
                        // CommuteArea 가 아닌 다른 PreferenceCategory 타입들 (Traffic, School, etc.)
                        else -> {
                            Column(modifier = Modifier.fillMaxWidth()) {
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
                    Text(
                        "데이터를 찾을 수 없습니다: ${tab.getOrNull(page)}",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        MainButton(
            onClick = {
                selectedOption?.let {
                    onFinish(
                        priorityIndex,
                        tab[pagerState.currentPage],
                        it
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .align(Alignment.BottomCenter),
            buttonSize = ButtonSize.L,
            text = stringResource(R.string.choose_btn_label),
        )


    }

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
                OnboardingStep.STEP1, OnboardingText.STEP1_GAP,
                mapOf(OnboardingStep.OPT_STEP5 to 1), {}, {}) { }
        }
    }
}

@Preview
@Composable
fun PreviewOPTStep5() {
    val rankList: List<RankedPriority> = listOf(RankedPriority(1, "유형", "신축"))
    val dummyPreferenceCategory = PreferenceCategory.CommuteArea(
        title = "출퇴근 지역",
        subCategories = listOf(
            PreferenceSubCategory("강남구", listOf("역삼동", "삼성동", "청담동")),
            PreferenceSubCategory("광화문", listOf("종로1가", "종로2가", "종로3가")),
            PreferenceSubCategory("성수동", listOf("성수1가", "성수2가"))
        )
    )
    Surface(modifier = Modifier.fillMaxSize(), color = White) {
        ImdangAppNewTheme() {
            OptStep5Screen(
                OnboardingText.STEP5_GAP,
                dummyPreferenceCategory,
                rankList,
                onNext = {},
                onSkip = {}
            ) { }
        }
    }
}


@Preview
@Composable
fun PreviewOPTStep6() {
    val dummyPreferenceCategory = PreferenceCategory.CommuteArea(
        title = "출퇴근 지역",
        subCategories = List(50) { index ->
            PreferenceSubCategory(
                name = "구역 $index",
                options = List(5) { optIndex -> "동 ${index + 1} - 동네 $optIndex" }
            )
        }
    )
    Surface(modifier = Modifier.fillMaxSize(), color = White) {
        ImdangAppNewTheme() {
            OptStep6Screen(
                OnboardingText.STEP6_GAP,
                dummyPreferenceCategory,
                onBackClick = {},
                onFinish = {},
                onSkip = {}
            )
        }
    }
}


@Preview
@Composable
fun PreviewFinishedScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = White) {
        ImdangAppNewTheme() {
            FinishedScreen()
        }
    }
}

