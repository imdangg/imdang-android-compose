package info.imdang.core.presentation.onboarding


enum class OnboardingText(
    val step: OnboardingStep,
    val purpose: UserPurpose,
    val title: String,
    val subtitle: String,
    val options: List<String> = emptyList()
) {
    /*------------실거주------------*/

    STEP1_REAL(
        step = OnboardingStep.STEP1,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = STEP1_TITLE,
        subtitle = STEP1_SUBTITLE,
        options = STEP1_OPTIONS
    ),

    STEP2_REAL(
        step = OnboardingStep.STEP2,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = STEP2_TITLE,
        subtitle = STEP2_SUBTITLE,
        options = STEP2_OPTIONS
    ),

    STEP3_REAL(
        step = OnboardingStep.STEP3,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = "누구와 함께 살 집을 찾고 계신가요?",
        subtitle = "",
        options = listOf(
            "신혼부부",
            "가족(3인 이상)",
            "중년부부",
            "혼자"
        )
    ),

    STEP4_REAL(
        step = OnboardingStep.STEP4,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = "아이와 함께 살고 계신가요?",
        subtitle = "",
        options = listOf(
            "자녀 계획 없음",
            "자녀 계획 있음",
            "1명",
            "2명",
            "3명"
        )
    ),
    STEP5_1_REAL(
        step = OnboardingStep.OPT_STEP5_1,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = STEP5_1_TITLE,
        subtitle = STEP5_1_SUBTITLE,
        options = listOf(COMMUTE_AREA, TRAFFIC, SCHOOL, INFRA, ENVIRONMENT)
    ),
    STEP5_2_REAL(
        step = OnboardingStep.OPT_STEP5_2,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = STEP5_2_TITLE,
        subtitle = STEP5_2_SUBTITLE
    ),

    /*------------갭투자------------*/

    STEP1_GAP(
        step = OnboardingStep.STEP1,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = STEP1_TITLE,
        subtitle = STEP1_SUBTITLE,
        options = STEP1_OPTIONS
    ),

    STEP2_GAP(
        step = OnboardingStep.STEP2,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = STEP2_TITLE,
        subtitle = STEP2_SUBTITLE,
        options = STEP2_OPTIONS
    ),

    STEP3_GAP(
        step = OnboardingStep.STEP3,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = "얼마 정도의 갭을 희망하시나요?",
        subtitle = "",
        options = listOf(
            "전세가율 90% 이상",
            "전세가율 80% 이상",
            "전세가율 70% 이상",
            "전세가율 60% 이상",
            "전세가율 50% 이상",
            "상관 없어요"
        )
    ),

    STEP4_GAP(
        step = OnboardingStep.STEP4,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = "얼마 동안 투자하실 계획이신가요?",
        subtitle = "",
        options = listOf(
            "1년 미만",
            "2년 미만",
            "3년 미만",
            "5년 미만",
            "10년 미만",
            "20년 미만",
            "아직 모르겠어요"
        )
    ),

    STEP5_1_GAP(
        step = OnboardingStep.OPT_STEP5_1,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = STEP5_1_TITLE,
        subtitle = STEP5_1_SUBTITLE,
        options = listOf(
            SQUARE_FOOTAGE, NUM_OF_HOUSEHOLDS, APT_CATEGORY, COMMUTE_AREA, INFRA, ENVIRONMENT
        )
    ),
    STEP5_2_GAP(
        step = OnboardingStep.OPT_STEP5_2,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = STEP5_2_TITLE,
        subtitle = STEP5_2_SUBTITLE
    ),
}

private const val STEP1_TITLE = "예산은 어느 정도 고려중이신가요?"
private const val STEP1_SUBTITLE = "현금+ 대출 총 예산을 선택해주세요"
private val STEP1_OPTIONS =
    listOf("1억 이하", "3억 이하", "7억 이하", "9억 이하", "15억 이하", "20억 이하", "30억 이하", "50억 이하")

private const val STEP2_TITLE = "월 수입이 어느정도 되시나요?(세후)?"
private const val STEP2_SUBTITLE = "맞벌이 부부라면 총합을 선택해주세요"
private val STEP2_OPTIONS = listOf(
    "수입 없음",
    "200만원 이하",
    "300만원 이하",
    "400만원 이하",
    "500만원 이하",
    "600만원 이하",
    "700만원 이하",
    "800만원 이하",
    "1,000만원 이상"
)

private const val STEP5_1_TITLE = "임장 우선순위를 골라주세요!"
private const val STEP5_1_SUBTITLE = "1,2,3위를 기준으로 컨텐츠를 추천해드려요."

private const val STEP5_2_TITLE = "관심 동네가 있으신가요?"
private const val STEP5_2_SUBTITLE = "*최대 3개까지 고를 수 있어요"